package com.ltybd.service;

import com.ltybd.common.cache.DataCache;
import java.util.Set;

/**
 * 缓存接口  使用这层将缓存进行隔离使用  方便维护与扩展
 */
public interface ICacheService {

    /**
     * 保存数据 data
     * @param dataCache
     */
    void save(DataCache dataCache);


    /**
     * 根据Key获取值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 根据key来进行模糊查询
     * @param key
     * @return
     */
    Set<String> like(String key);

    /**
     * 修改key
     * @param oldKey
     * @param newKey
     */
    void updateKey(String oldKey,String newKey);
}
