package com.ltybd.service.impl.cache;

import com.alibaba.dubbo.common.json.ParseException;
import com.ltybd.common.cache.DataCache;

import java.util.Set;

public class Memcached extends AbsCache {

    @Override
    void doSave(DataCache dataCache) {

    }

    @Override
    String doGet(String key)throws ParseException {
        return null;
    }

    @Override
    Set<String> doLike(String key) {
        return null;
    }


    @Override
    public void updateKey(String oldKey, String newKey) {

    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void del(String key) {
        return;
    }
}
