package com.fh.student.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDataPool {
    private static JedisPool jedisPool;
    private RedisDataPool(){
    }
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        jedisPool = new JedisPool("192.168.75.129", 6379);
    }
    public static Jedis getJedis(){
        Jedis resource = jedisPool.getResource();
        return resource;
    }
    public static void returnRedisSource(Jedis jedis){
        if (jedis!=null){
            jedis.close();
        }
    }
}
