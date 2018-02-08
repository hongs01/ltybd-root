package com.ltybd.PayStateMachine;


import com.ltybd.SpringUtil;
import com.ltybd.common.domain.PayStateData;
import com.ltybd.common.enums.PayDataState;
import com.ltybd.service.ICacheService;
import com.ltybd.service.IPayLogSearchService;
import com.ltybd.util.JsonModelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 *@author zhouyongbo 2017/11/01
 * 支付数据监控
 */
public class PayMonitor implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(PayMonitor.class);
    private static ICacheService iCacheService = SpringUtil.getBeanByClass(ICacheService.class);
    private static IPayLogSearchService iPayLogSearchService =  SpringUtil.getBeanByClass(IPayLogSearchService.class);

    private int intervalTime; // 监控时间
    private int intervalNum;// 监控间隔次数
    private int initTimer;//初始化时间隔时间

    private PayStateData payStateData;// 支付状态数据

    public PayMonitor(int intervalTime, int intervalNum, int initTimer, PayStateData take) {
        this.intervalTime = intervalTime;
        this.intervalNum = intervalNum<= 0 ? 1:intervalNum;
        this.initTimer = initTimer;
        this.payStateData = take;
    }

    @Override
    public void run() {
        if (initTimer > 0) {
            try {
                Thread.sleep(initTimer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 第一步 看数据是什么状态 状态为0的  则是elasticserach 验证数据
        //状态为2的  则是从直接从队列中拿数据给到elasticserach
        //状态为3的  则不存在 (数据已完成存储)
        // 第二步 拿 elasticsearch 数据 如果没拿到  则从redis里面拿数据 给到elasticserch
        char state = payStateData.getState();
        //MQ异常  则是从redis直接拿数据 给到elasticserach
        if (state == PayDataState.EXCEPTION_MQ.getKey()) {
            //拿到缓存数据
            dispose();
        }else if (state == PayDataState.EXCEPTION_ALL.getKey()){ // 组件都出错的情况 直接进行存储
            if (!checkData()){
                iPayLogSearchService.savePayLog(payStateData.getData());
                logger.info("服务器直存 key:"+payStateData.getKey()+";data:"+JsonModelUtils.jsonModle(payStateData.getData()));
            }
        }else if (state == PayDataState.RUN.getKey() || state == PayDataState.EXCEPTION_RREDIS.getKey()) {
            if (intervalNum == 1) {
                if (checkData()) {
                    return;
                }
            } else {
                for (int i = 1; i <= intervalNum; i++) {
                    if (checkData()) {
                        return;
                    }
                    try {
                        Thread.sleep(intervalTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            dispose();
        }

    }

    private void dispose(){
        logger.info("开始存入数据", payStateData.getKey());
        char state = payStateData.getState();
        //先从redis里面拿数据
        String oldkey = payStateData.getKey() + ":" + payStateData.getState();

        String sData = null;
        if (state == PayDataState.RUN.getKey()){
            sData = iCacheService.get(oldkey);
        }else if(state == PayDataState.EXCEPTION_MQ.getKey() ){
            sData = iCacheService.get(oldkey);
            if (sData == null ){
                sData = JsonModelUtils.jsonModle(payStateData.getData());
            }
        }else if (state == PayDataState.EXCEPTION_RREDIS.getKey()){
            sData = JsonModelUtils.jsonModle(payStateData.getData());
        }

        if (sData == null) {
            logger.error("KEY: " + oldkey + ": "+state+";" + "取数据出现错误 ; ");
            return;
        }
        //存入 elasticsearch;
        try{
            iPayLogSearchService.savePayLog(sData);
            logger.info("redis 转存 key:"+payStateData.getKey()+";data:"+sData);
            //状态改变
            completeData();
        }catch (Exception e){
            logger.error("错误: + oldkey:"+payStateData.getKey() + ":"+payStateData.getState() +oldkey,e);
        }

        //改变redis的状态
    }

    /**
     * 校验数据是否同步过来了
     * @return
     */
    private boolean checkData(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("body.key.keyword",payStateData.getKey());

        Map<String, Object> one = iPayLogSearchService.findOne(map);
        if (one != null && one.size() >= 0){
            completeData();
            return true;
        }
        return false;
    }

    /**
     * 完成状态改变状态
     */

    private void completeData(){
        String oldkey = payStateData.getKey() + ":"+payStateData.getState();
        iCacheService.updateKey(oldkey,payStateData.getKey()+":"+PayDataState.COMPLETE.getKey());
    }
}
