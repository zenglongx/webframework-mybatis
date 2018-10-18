package com.xx.webframework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTools {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public void setString(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public <T> void setObj(String key, T obj){
        redisTemplate.opsForValue().set(key,obj);
    }

    public <T> T getObj(String key){
        return (T)redisTemplate.opsForValue().get(key);
    }
}
