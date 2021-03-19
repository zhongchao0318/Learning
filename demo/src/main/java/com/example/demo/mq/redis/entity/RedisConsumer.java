package com.example.demo.mq.redis.entity;

/**
 * @Author: zc
 * @Date: 2021/3/11 13:34
 */
public interface RedisConsumer {
    /**
     * 消费方法，消费者必须继承此方法
     *
     * @param message
     */
    void deal(String message);
}
