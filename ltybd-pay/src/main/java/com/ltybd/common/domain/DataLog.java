package com.ltybd.common.domain;

import java.io.Serializable;

/**
 * zhouyongbo 2017/10/27
 * 后面异步处理进行流程扭转的时候进行使用
 */
public class DataLog<T extends Serializable> implements Serializable {
    private String key;//唯一标识服
    private T data;//支付记录
    private Character isStore;//是否已经存储 默认为0  0:未存储 1 kafka 异常 2 redis 异常  3

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public char getIsStore() {
        return isStore;
    }

    public void setIsStore(char isStore) {
        this.isStore = isStore;
    }
}
