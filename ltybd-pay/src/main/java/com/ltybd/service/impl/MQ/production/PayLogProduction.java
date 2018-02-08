package com.ltybd.service.impl.MQ.production;

import com.alibaba.dubbo.common.json.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;


/**
 * @author zhouyongbo 2017/10/28
 * 支付记录消息生产
 */
@Component
public class PayLogProduction extends ApplyAbsMqProduction{


    public PayLogProduction() {
        super("payLog");
    }

    public void sendMsg(Object msg) {
        String msgJson = null;
        try {
            msgJson = JSON.json(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.execute(msgJson);
    }
}
