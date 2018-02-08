package com.ltybd.common.cache;


import com.ltybd.PayStateMachine.properties.PayProperties;
import com.ltybd.common.enums.BeUseingType;
import com.ltybd.common.enums.CacheType;
import com.ltybd.service.IAbsCache;
import com.ltybd.service.impl.cache.Memcached;
import com.ltybd.service.impl.cache.RedisCache;
import com.ltybd.service.impl.cache.XyyRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheConfig {

    @Autowired
    private PayProperties payProperties;

    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<String, String>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    //默认为redis 单节点 缓存
    @Bean
    public IAbsCache iAbsCache(){
        String cacheUseType = payProperties.getCacheUseType();
        if (cacheUseType == null || "".equals(cacheUseType)){
            return new RedisCache();
        }
        CacheType type = CacheType.getTypeCacheType(cacheUseType);
        if ( type == CacheType.REDIS){
            return new RedisCache();
        }else if (type == CacheType.MEMCACHEED){
            return new Memcached();
        }else if (type == CacheType.REDISCOLONY){
            return new XyyRedisTemplate();
        }
        return new RedisCache();
    }

}
