package com.ltybd.common.cache;

/**
 *  zhouyongbo 2017/10/27
 *  缓存参数类型
 */
public class DataCache{
    private String key; //key值
    private String data;// value 需要保存的数据
    private int timer;// 单位为秒 s   小于等于0为永久

    public DataCache() {
    }

    public DataCache(String key, String data) {
        this.key = key;
        this.data = data;
    }

    public DataCache(String key, String data, int timer) {
        this.key = key;
        this.data = data;
        this.timer = timer;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
