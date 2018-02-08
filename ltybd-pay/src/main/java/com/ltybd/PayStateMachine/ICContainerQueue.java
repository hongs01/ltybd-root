package com.ltybd.PayStateMachine;

import com.ltybd.common.domain.PayStateData;
import com.ltybd.common.enums.CacheKeyType;
import com.ltybd.common.enums.PayDataState;
import com.ltybd.service.ICacheService;
import com.ltybd.PayStateMachine.properties.ServiceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author zhouyongbo 2017/10/31
 * 容器操作
 */
@Component
public class ICContainerQueue {

    private static Logger logger = LoggerFactory.getLogger(ICContainerQueue.class);

//    @Value("${server.identifying}")
//    private String serviceId;

    @Autowired
    ICacheService iCacheService;

    @Autowired
    private ServiceProperties servicePropertis;

    /**
     * 添加元素
     * @param payStateData
     */
    public boolean add(PayStateData payStateData){
        return ContainerQueue.add(payStateData);
    }

    public void addFirst(PayStateData payStateData){
        ContainerQueue.addFirst(payStateData);
    }

    public void addLast(PayStateData payStateData){
        ContainerQueue.addLast(payStateData);
    }
    /**
     * 取元素
     * @return
     */
    public PayStateData poll(){
        return ContainerQueue.poll();
    }


    /**
     * 等待取元素
     * @return
     * @throws InterruptedException
     */
    public PayStateData take() throws InterruptedException {
        return ContainerQueue.take();
    }

    /**
     * 容器初始化
     */
    public void init(){
        //先找状态为1的 再找找状态为0的数据, 急需存储的数据
        String key = CacheKeyType.getCacheKey(CacheKeyType.PAYLOG,servicePropertis.getServiceId()+":*");
//        Set<String> likeOne = iCacheService.like(key + ":1");
//        for (String keyQ:likeOne){
//            add(new PayStateData(keyQ.substring(0,keyQ.lastIndexOf(":")),null,'1'));
//        }
        //再找状态为0的数据 ,需验证的数据
//        Set<String> likeZero = iCacheService.like(key + ":0");
//        for (String keyQ:likeOne){
//            add(new PayStateData(keyQ.substring(0,keyQ.lastIndexOf(":")),null,'0'));
//        }
        Set<String> like = iCacheService.like(key);
        for (String keyQ:like){
            if (keyQ.endsWith(":"+ PayDataState.EXCEPTION_MQ.getKey())){
                add(new PayStateData(keyQ.substring(0,keyQ.lastIndexOf(":")),null, PayDataState.EXCEPTION_MQ.getKey()));
            }else if (keyQ.endsWith(":" + PayDataState.RUN.getKey())){
                add(new PayStateData(keyQ.substring(0,keyQ.lastIndexOf(":")),null, PayDataState.RUN.getKey()));
            }
        }
    }
}
