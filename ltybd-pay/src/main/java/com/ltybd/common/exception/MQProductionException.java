package com.ltybd.common.exception;

/**
 * @author zhouyongbo 2017/10/28
 *MQ生产  异常消息
 */
public class MQProductionException extends RuntimeException {

    public MQProductionException(String message) {
        super(message);
    }
}
