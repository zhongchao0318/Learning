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
        System.out.println("A:" + submit("A", "11"));
        System.out.println("A:" + submit("A", ""));
        System.out.println("B:" + submit("B", "2"));
        System.out.println("B2:" + submit("B", ""));
        System.out.println("C:" + submit("C", "11"));
        System.out.println("C2:" + submit("C", ""));
        System.out.println("D:" + submit("D", "11"));
        System.out.println("D2:" + submit("D", ""));
        System.out.println("E:" + submit("E", "11"));
        System.out.println("E2:" + submit("E", ""));
        System.out.println("F:" + submit("F", "11"));
        System.out.println("F2:" + submit("F", ""));
        System.out.println("G:" + submit("G", "11"));
        System.out.println("G2:" + submit("G", ""));
        System.out.println("H:" + submit("H", "11"));
        System.out.println("H2:" + submit("H", ""));
        System.out.println("i:" + submit("i", "11"));
        System.out.println("i2:" + submit("i", ""));

    }

    private static boolean submit(String reason, String date) {
        String oaPassReason = reason;//资质原因
        String promiseDate = date == null ? "" : date;//资质不全日期

        if ((!"A".equals(oaPassReason)
                && (!"B".equals(oaPassReason) || "".equals(promiseDate))
                && (!"G".equals(oaPassReason) || "".equals(promiseDate))
                && (!"H".equals(oaPassReason) || "".equals(promiseDate))
        ) || "F".equals(oaPassReason)) {
            return false;
        } else {
            return true;
        }

//        if ("A".equals(oaPassReason)) {
//            return true;
//        } else if ("B".equals(oaPassReason) && !"".equals(promiseDate)) {
//            return true;
//        } else if ("G".equals(oaPassReason) && !"".equals(promiseDate)) {
//            return true;
//        } else if ("H".equals(oaPassReason) && !"".equals(promiseDate)) {
//            return true;
//        } else if ("F".equals(oaPassReason)) {
//            return false;
//        } else {
//            return false;
//        }
    }
}
