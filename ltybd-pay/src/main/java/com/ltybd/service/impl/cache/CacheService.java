package com.ltybd.service.impl.cache;

import com.alibaba.dubbo.common.json.ParseException;
import com.ltybd.common.cache.DataCache;
import com.ltybd.service.IAbsCache;
import com.ltybd.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * zhouyongbo 2017/10/27
 */
@Service
public class CacheService implements ICacheService {

    IAbsCache iAbsCache;

    @Autowired
    public void setiAbsCache(IAbsCache iAbsCache) {
        this.iAbsCache = iAbsCache;
    }
    @Override
    public void save(DataCache dataCache) {
        iAbsCache.save(dataCache);
    }

    @Override
    public String get(String key) {
        try {
            return iAbsCache.get(key);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> like(String key) {
        return iAbsCache.like(key);
    }

    @Override
    public void updateKey(String oldKey, String newKey) {
        iAbsCache.updateKey(oldKey,newKey);
    }
}
