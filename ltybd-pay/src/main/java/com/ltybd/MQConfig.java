package com.ltybd;

import com.ltybd.common.MQ.MQType;
import com.ltybd.common.enums.BeUseingType;
import com.ltybd.service.IAbsMqProduction;
import com.ltybd.service.impl.MQ.production.KafkaProduction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ使用配置文件
 *
 */
@Configuration
public class MQConfig {


    //默认为kafka类型
    @Bean
    public IAbsMqProduction getMqProduction(){
            Object type = BeUseingType.USEMQ.getType();
        if ( type == MQType.KAFKA){
            return new KafkaProduction();
        }
        return new KafkaProduction();
    }

}
