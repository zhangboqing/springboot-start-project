package com.example.startdemo.common.utils.redis;

import com.alibaba.fastjson.JSON;
import com.example.startdemo.common.utils.SpringBeanUtils;
import com.example.startdemo.common.utils.redis.constant.ExpireTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * redis缓存工具类
 * 注意事项:
 * 使用之前,请初始化redis链接
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisUtils {

    private static RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) SpringBeanUtils.getBean("stringRedisTemplate");

    /**
     * 将value对象写入缓存
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    public static void set(String key,Object value,ExpireTime time){
        set(key,value,time.getTime());
    }

    /**
     * 将value对象写入缓存
     * @param key
     * @param value
     * @param seconds 失效时间(秒)
     */
    public static void set(String key,Object value,int seconds){
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
        if(seconds > 0){
            redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        }
    }


    public static void set(String key,String hashKey,Object value,int seconds){
        redisTemplate.opsForHash().put(key,hashKey, JSON.toJSONString(value));
        if(seconds > 0){
            redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        }
    }

    /**
     * 将value对象写入缓存,不失效
     * @param key
     * @param value
     */
    public static void set(String key,Object value){
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, JSON.toJSONString(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存<br>
     * 注：基本数据类型(Character除外)，请直接使用get(String key, Class<T> clazz)取值
     * @param key
     * @return
     */
    public static <T> T get(String key, Class<T> clazz){
        String value = redisTemplate.boundValueOps(key).get();
        return (T)JSON.parseObject(value,clazz);
    }

    public static <T> T get(String key,String hashKey, Class<T> clazz){
        String value = (String) redisTemplate.opsForHash().get(key,hashKey);
        return (T)JSON.parseObject(value,clazz);
    }


    public static void incr(String key,String hashKey){
         redisTemplate.opsForHash().increment(key,hashKey,1);
    }


    /**删除**/
    public static void delete(String key) {
        redisTemplate.delete(key);
    }



}