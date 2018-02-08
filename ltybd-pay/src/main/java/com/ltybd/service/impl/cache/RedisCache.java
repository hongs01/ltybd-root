package com.ltybd.service.impl.cache;

import com.ltybd.common.cache.DataCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import java.util.Set;

public class RedisCache extends AbsCache {

    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
//        redisTemplate.setKeySerializer(new StringRedisSerializer() );
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Object.class));
    }

    @Override
    void doSave(DataCache dataCache) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        if (dataCache.getTimer()<=0){
            valueOperations.set(dataCache.getKey(),dataCache.getData());
        }else {
            valueOperations.set(dataCache.getKey(),dataCache.getData(),dataCache.getTimer()*1000);
        }
    }

    @Override
    String doGet(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String o = valueOperations.get(key);
        if (o == null ){
            return null;
        }
        return o;
    }

    @Override
     Set<String> doLike(String key) {
        Set<String> keys = redisTemplate.keys(key);
        return keys;
    }

    @Override
    public void updateKey(String oldKey, String newKey) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String o = valueOperations.get(oldKey);
        redisTemplate.delete(oldKey);
        valueOperations.set(newKey,o);
    }

    @Override
    public String get(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
         return valueOperations.get(key);
    }

    @Override
    public void del(String key) {
    }
}
