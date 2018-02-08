package com.ltybd.util;

/**
 * zhouyongbo 2017/10/27
 * 支付记录一些工具类
 */
public class PayLogUtils {

    /**
     * 支付记录 存入redis 中的key
     * @param serviceId  服务ID
     * @param state  状态码
     * @return
     */
    public static String redisKey(String serviceId,int state){
        return serviceId+":"+UUIDUtils.getUUID()+":"+state;
    }

    /**
     * 支付记录 存入redis 中的key
     * @param serviceId  服务ID
     * @return
     */
    public static String redisKey(String serviceId){
        return serviceId+":"+UUIDUtils.getUUID();
    }
}
