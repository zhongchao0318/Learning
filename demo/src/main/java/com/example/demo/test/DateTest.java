package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: zc
 * @Date: 2020/12/1 16:41
 */
public class DateTest {
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //最开始是2007-001
    int year = 2007;
    int number = 1;
    private static String numberStr = "";

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        int year = 2007;
        int number = 1;//期数
        boolean flag = true;
        JSONObject body = new JSONObject();
        JSONObject param = new JSONObject();
        while (flag) {
            body.clear();
            param.clear();
            param.put("GameCode", "DLT");
            param.put("IssuseNumber", year + "-" + getNumberStr(number));
            body.put("Param", param);
            System.out.println(body.toString());
            //发送请求处理数据
            if (number > 156) {
                number = 1;
                year++;
            } else {
                number++;
            }
            Thread.sleep(2000);
            if (year == 2020 && number == 122) {
                flag = false;
            }
            count++;
        }
        System.out.println("count:" + count);
    }

    private static String getNumberStr(int number) {
        numberStr = "";
        if (number < 10) {
            numberStr = "00" + number;
        } else if (number < 100) {
            numberStr = "0" + number;
        } else {
            numberStr = "" + number;
        }
        return numberStr;
    }
}
