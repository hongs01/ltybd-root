package com.ltybd.PayStateMachine.properties;

/**
 * Created by zhouyongbo on 2017-11-4.
 * 关于 支付数据监控的配置
 */
public class PayData {

    private int intervalTime; // 监控时间
    private int intervalNum;// 监控间隔次数
    private int initTimer;//初始化时间隔时间

    public int getIntervalTime() {
        return intervalTime;
    }

    public int getIntervalNum() {
        return intervalNum;
    }

    public int getInitTimer() {
        return initTimer;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public void setIntervalNum(int intervalNum) {
        this.intervalNum = intervalNum;
    }

    public void setInitTimer(int initTimer) {
        this.initTimer = initTimer;
    }
}
