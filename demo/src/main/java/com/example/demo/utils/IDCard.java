package com.example.demo.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 省市县代码
 * http://www.ip33.com/area_code.html
 */
public class IDCard {
    //系数
    private static final int[] coefficients = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6,
            3, 7, 9, 10, 5, 8, 4, 2};
    //校验码
    private static final String[] last = new String[]{"1", "0", "X", "9", "8", "7",
            "6", "5", "4", "3", "2"};
    //省
    private static final Map<String, String> PROVINCE = new HashMap<>();
    //市
    private static final Map<String, String> CITY = new HashMap<>();
    //县
    private static final Map<String, String> COUNTY = new HashMap<>();

    static {
        PROVINCE.put("11", "北京市");
        COUNTY.put("110101", "东城区");
        COUNTY.put("110102", "西城区");
        COUNTY.put("110228", "密云县");
        PROVINCE.put("12", "天津市");
        COUNTY.put("120101", "和平区");
        COUNTY.put("120221", "宁河县");
        PROVINCE.put("43", "湖南省");
        CITY.put("4301", "长沙市");
        COUNTY.put("430102", "芙蓉区");
        CITY.put("4308", "张家界市");
        COUNTY.put("430822", "桑植县");
        System.out.println("----------");
    }
    public static void main(String[] args) {
        chackCode();
    }
    private static void chackCode() {
        String card = "430822197908212227";
        char[] cars = card.toCharArray();
        int sum = 0;
        int size = cars.length == 18 ? 18 - 1 : 17;
        for (int i = 0; i < size; i++) {
            final int i1 = Integer.valueOf(String.valueOf(cars[i]));
            sum = sum + i1 * coefficients[i];
        }
        System.out.println("尾数:\t" + (last[sum % 11]));
    }
}
