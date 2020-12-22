package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: zc
 * @Date: 2020/12/7 16:02
 */
public class FastJsonText {

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        String a = json.getString("aaa");
        System.out.println("".equals(a));

    }

}
