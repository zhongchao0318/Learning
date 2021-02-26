package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @Author: zc
 * @Date: 2021/1/20 16:11
 */
@Slf4j
public class IfElseTest {

    public static void main(String[] args) {
        boolean status = false;
        int count = 0;
        while (!status) {
            System.out.println(count);
            status = count >= 9 ? true : false;
            count++;
        }

    }
}
