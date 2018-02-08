package com.ltybd.service;

import com.ltybd.common.MQ.DataMQ;

/**
 * @author  zhouyongbo 2017/10/28
 * MQ 消息生产抽象接口
 */
public interface IAbsMqProduction {

    void sendMsg(DataMQ o);
}
