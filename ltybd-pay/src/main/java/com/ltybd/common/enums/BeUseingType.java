package com.ltybd.common.enums;

import com.ltybd.common.MQ.MQType;

/**
 *@author zhouyongbo 2017/10/28
 * 正在使用中的中间件
 */
public enum  BeUseingType {
    //缓存使用类型
    USECACHETYPE(CacheType.REDIS),
    USEMQ(MQType.KAFKA);
    private Object type;

    BeUseingType(Object type) {
        this.type = type;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }
}
