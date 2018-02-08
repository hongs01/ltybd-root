package com.ltybd.service.impl;

import com.ltybd.PayStateMachine.ICContainerQueue;
import com.ltybd.common.cache.DataCache;
import com.ltybd.common.domain.MQHeard;
import com.ltybd.common.domain.MQProtocol;
import com.ltybd.common.domain.PayStateData;
import com.ltybd.common.enums.CacheKeyType;
import com.ltybd.common.enums.PayDataState;
import com.ltybd.common.exception.PayException;
import com.ltybd.entity.PayRecord;
import com.ltybd.service.IApplyAbsMqProduction;
import com.ltybd.service.ICacheService;
import com.ltybd.service.IPayLogSearchService;
import com.ltybd.service.IPayLogService;
import com.ltybd.util.JsonModelUtils;
import com.ltybd.util.PayLogUtils;
import com.ltybd.PayStateMachine.properties.ServiceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayLogService implements IPayLogService {

//    @Value("${server.identifying}")
//    private String serviceId;

    @Autowired
    private ICacheService iCacheService;

    @Autowired
    @Qualifier("payLogProduction")
    private IApplyAbsMqProduction iApplyAbsMqProduction;

    @Autowired
    private IPayLogSearchService iPayLogSearchService;

    @Autowired
    private ICContainerQueue icContainerQueue;

    @Autowired
    private ServiceProperties servicePropertis;

    private static Logger logger = LoggerFactory.getLogger(PayLogService.class);

    @Override
    public int savePayLog(PayRecord payRecord) {
        PayDataState state = PayDataState.RUN;
        String key = CacheKeyType.getCacheKey(CacheKeyType.PAYLOG, PayLogUtils.redisKey(servicePropertis.getServiceId()));

        //MQ发送消息
        payRecord.setKey(key);
        MQProtocol mqProtocol = new MQProtocol(new MQHeard(2, 4, 4), payRecord);
        try{
//            throw new RuntimeException("lll");
            iApplyAbsMqProduction.sendMsg(mqProtocol);
        }catch (Exception e){
            logger.error("MQ 消息发送异常",e.getMessage());
            state = PayDataState.EXCEPTION_MQ;
        }

        //redis 存储参数
        //先转流程数据
        try{
            String keyR = key+ ":"+ state.getKey();
            payRecord.setKey(key);
            mqProtocol.setBody(payRecord);
            DataCache payLogDataCache = new DataCache(keyR, JsonModelUtils.jsonModle(mqProtocol));
            iCacheService.save(payLogDataCache);
        }catch (Exception e){
            logger.error("redis 出错",e);
            if (state == PayDataState.EXCEPTION_MQ){
                state = PayDataState.EXCEPTION_ALL;
            }else {
                state =  PayDataState.EXCEPTION_RREDIS;
            }

        }

        //支付状态机,为避免数据损失 状态为2的  直接存储  优先进入队列头
        if (state == PayDataState.EXCEPTION_ALL){ //最优先处理
            payRecord.setKey(key);
            mqProtocol.setBody(payRecord);
            icContainerQueue.addFirst(new PayStateData(key,mqProtocol,state.getKey()));
        }else if (state == PayDataState.EXCEPTION_MQ || state == PayDataState.EXCEPTION_RREDIS){ //第二优先级处理
            payRecord.setKey(key);
            mqProtocol.setBody(payRecord);
            icContainerQueue.add(new PayStateData(key,mqProtocol,state.getKey()));
        }else { //最后处理
            icContainerQueue.addLast(new PayStateData(key,state.getKey()));
        }
        return 0;
    }

    @Override
    public Map<String, Object> getPayLog(String key) throws PayException {
        Map<String,String> map=new HashMap<String,String>();
        map.put("body.key.keyword",key);
        return  iPayLogSearchService.findOne(map);
    }
}
