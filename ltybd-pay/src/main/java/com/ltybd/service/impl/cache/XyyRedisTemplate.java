package com.ltybd.service.impl.cache;

import com.alibaba.dubbo.common.json.ParseException;
import com.ltybd.common.cache.DataCache;
import com.ltybd.common.cache.redis.RedisProperties;
import com.ltybd.util.JsonModelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Redis 集群模版配置
 * 默认使用json来进行序列化
 */
public class XyyRedisTemplate extends AbsCache {

    private static final Logger logger    = LoggerFactory.getLogger(XyyRedisTemplate.class);

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    void doSave(DataCache dataCache) {
        if (dataCache.getTimer() > 0 ){
            jedisCluster.setex(dataCache.getKey(), dataCache.getTimer(),dataCache.getData());
        }else {
            jedisCluster.set(dataCache.getKey(), dataCache.getData());
        }


    }

    @Override
    String doGet(String key) throws ParseException {
        return jedisCluster.get(key);
    }

    @Override
    Set<String> doLike(String key) {
        logger.debug("Start getting keys...");
        TreeSet<String> keys = new TreeSet<>();
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for(String k : clusterNodes.keySet()){
            logger.debug("Getting keys from: {}", k);
            JedisPool jp = clusterNodes.get(k);
            Jedis connection = jp.getResource();
            try {
                keys.addAll(connection.keys(key));
            } catch(Exception e){
                logger.error("Getting keys error: {}", e);
            } finally{
                logger.debug("Connection closed.");
                connection.close();//用完一定要close这个链接！！！
            }
        }
        logger.debug("Keys gotten!");
        return keys;
//        return jedRRisCluster.hkeys(key);
    }

    @Override
    public void updateKey(String oldKey, String newKey) {
        String value = jedisCluster.get(oldKey);
        jedisCluster.del(oldKey);
        jedisCluster.set(newKey,value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public void del(String key) {
        jedisCluster.del(key);
    }
}
