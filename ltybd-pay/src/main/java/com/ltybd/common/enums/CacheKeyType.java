package com.ltybd.common.enums;

/**
 * zhouyongbo 2017/10/27
 * 缓存key的类型组装
 */
public enum CacheKeyType {
    PAYLOG("PAYLOG");//支付记录
    private String key;

    /**
     * 缓存key值
     * @param cacheType
     * @param lastKey
     * @return
     */
    public static String getCacheKey(CacheKeyType cacheType, String lastKey){
        return cacheType.key+":"+lastKey;
    }

    CacheKeyType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
