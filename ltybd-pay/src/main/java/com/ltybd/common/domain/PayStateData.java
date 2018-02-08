package com.ltybd.common.domain;


/**
 * @author zhouyongbo 2017/10/31
 * 支付队列数据
 */
public class PayStateData {
    private String key;
    private Object data;
    private char state;

    public PayStateData(String key, char state) {
        this.key = key;
        this.state = state;
    }

    public PayStateData(String key, Object data, char state) {
        this.key = key;
        this.data = data;
        this.state = state;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }
}
