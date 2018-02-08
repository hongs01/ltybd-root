package com.ltybd.service.impl.MQ.production;

import com.ltybd.common.MQ.DataMQ;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author zhouyongbo 2017/10/28
 * kafka 具体实现
 */
public class KafkaProduction extends AbsMqProduction {

    @Autowired
    KafkaTemplate kafkaTemplate;

//    @Autowired
//    KafkaConsumer kafkaConsumer;

    @Override
    public void sendMsg(DataMQ dataMQ) {
        kafkaTemplate.send(dataMQ.getTopicName(),dataMQ.getData());
    }



//    public void consumer(){
//        ConsumerRebalanceListener consumerRebalanceListener = new NoOpConsumerRebalanceListener();
//        consumerRebalanceListener.
//        kafkaConsumer.
//    }
}
