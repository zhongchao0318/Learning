package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.exception.DemoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Author: zc
 * @Date: 2020/12/7 16:02
 */
public class FastJsonText {
    private static Logger logger = LoggerFactory.getLogger(FastJsonText.class);

    public static void main(String[] args) {
        String aa = null;
        JSONObject res = JSON.parseObject(aa);
        System.out.println(res);
    }

}

class Son {
    private String name;
    private String age;
    private boolean flag;

    public Son() {
    }

    public Son(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
