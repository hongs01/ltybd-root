package com.ltybd.service.impl.cache;

import com.alibaba.dubbo.common.json.ParseException;
import com.ltybd.common.cache.DataCache;
import com.ltybd.service.IAbsCache;
import java.util.Set;

/**
 * zhouyongbo 2017/10/27
 * 缓存抽象类
 */
public abstract class AbsCache implements IAbsCache {

    //--------------保存-----------
    abstract  void doSave(DataCache dataCache);
    @Override
    public void save(DataCache dataCache) {
        doSave(dataCache);
    }


    //--------------获取--------------
    abstract String doGet(String key)  throws ParseException;

    @Override
    public String get(String key) throws ParseException {
        return doGet(key);
    }


    //--------------模糊查询-------------
    abstract Set<String>  doLike(String key);
    @Override
    public Set<String> like(String key) {
        return doLike(key);
    }
}
