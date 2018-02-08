package com.ltybd.service;

import com.alibaba.dubbo.common.json.ParseException;
import com.ltybd.common.cache.DataCache;

import java.util.List;
import java.util.Set;

/**
 * @author  zhouyongbo 2017/10/27
 * 缓存使用接口
 */
public interface IAbsCache {

    /**
     * 保存数据 data
     * @param dataCache
     */
    void save(DataCache dataCache);


    /**
     * 修改key
     * @param oldKey
     * @param newKey
     */
    void updateKey(String oldKey,String newKey);


    /**
     * 根据KEY获取值
     * @param key
     * @return
     */
    String get(String key) throws ParseException;

    /**
     * 根据key来进行模糊查询
     * @param key
     * @return
     */
    Set<String> like(String key);


    /**
     * 删除key
     * @param key
     */
    void del(String key);



}
