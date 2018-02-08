package com.ltybd.common.enums;

/**
 * zhouyongbo 2017/10/27
 * 缓存实现类型
 */
public enum CacheType {
    REDIS("redis"),//redis
    REDISCOLONY("redisColony"),//redis 集群
    MEMCACHEED("memcached");//Memcached


    CacheType(String key) {
        this.key = key;
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static CacheType getTypeCacheType(String key){
        CacheType[] cacheTypes = CacheType.values();
        for (CacheType cacheType:cacheTypes){
            if (cacheType.getKey().equals(key)){
                return cacheType;
            }
        }
        return null;
    }
}
