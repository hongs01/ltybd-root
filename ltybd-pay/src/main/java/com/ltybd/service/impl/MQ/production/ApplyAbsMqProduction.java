package com.ltybd.service.impl.MQ.production;

import com.ltybd.common.MQ.DataMQ;
import com.ltybd.service.IAbsMqProduction;
import com.ltybd.service.IApplyAbsMqProduction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhouyongbo 2017/10/28
 * MQ实际应用层
 */
public abstract class ApplyAbsMqProduction implements IApplyAbsMqProduction {

    private String topicName;

    @Autowired
    private IAbsMqProduction iAbsMqProduction;


    public ApplyAbsMqProduction(String topicName) {
        this.topicName = topicName;
    }

    //----------------------------- 生产消息----------------------
    protected void execute(Object msg ) {
        iAbsMqProduction.sendMsg(new DataMQ(topicName,msg));
    }
    //-----------------------------

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
