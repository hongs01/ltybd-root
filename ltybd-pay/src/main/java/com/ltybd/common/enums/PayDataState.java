package com.ltybd.common.enums;

/**
 * Created by zhouyongbo on 2017-11-4.
 *  支付流程数据状态
 */
public enum PayDataState {
    RUN('0'), // 运行中
    EXCEPTION_MQ('1'),//MQ异常
    EXCEPTION_RREDIS('2'),//REDIS 异常
    COMPLETE('3'),//完成
    EXCEPTION_ALL('9');//所有组件 同时异常

    private Character key;

    PayDataState(Character key) {
        this.key = key;
    }

    public Character getKey() {
        return key;
    }

    public void setKey(Character key) {
        this.key = key;
    }
}
