package com.example.demo.mq.redis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义消费者注解
 *
 * @Author: zc
 * @Date: 2021/3/11 13:29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface MqConsumer {
    /**
     * 队列主题
     *
     * @return
     */
    String topic() default "default_es_topic";
}
