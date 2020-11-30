package com.example.demo;

import com.alibaba.fastjson.*;
import com.example.demo.rsa.RsaSecretKey;
import com.example.demo.utils.redis.jedis.RedisUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    void password() {


    }


    @Resource
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        RsaSecretKey rsaObject = new RsaSecretKey("key123", "key_pub_123");
        String res = redisUtil.set("zhangsan", JSONArray.toJSON(rsaObject).toString(), 0);
        logger.info(rsaObject.toString());
        logger.info("插入结果：{}", res);
        res = null;
        res = redisUtil.get("zhangsan", 0); //根据key获取value
        logger.info(res == null ? "res is null" : res);
        boolean flag = redisUtil.exists("zhangsan1");//是否存在
        logger.info("exists {}", flag);
        RsaSecretKey obj = (RsaSecretKey) JSONArray.parseObject(res, RsaSecretKey.class);
        logger.info("key:{}\tkey_pub:{}", obj.getPrivateKey(), obj.getPublicKey());
        redisUtil.expire("zhangsan1", 60, 0);//设置过期
    }

}
