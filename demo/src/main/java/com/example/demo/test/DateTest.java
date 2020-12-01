package com.example.demo.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/1 16:41
 */
public class DateTest {
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        CALENDAR.set(Calendar.MONTH, 10);//设置月
        CALENDAR.set(Calendar.DATE, 28);//设置日期
        List<String> timeList = new ArrayList<>();
        for (int i = 0; i < (52 * 4) - 4; i++) {
            timeList.add(sdf.format(CALENDAR.getTime()));
            CALENDAR.set(CALENDAR.DATE, CALENDAR.get(CALENDAR.DATE) - 3);//周三
            timeList.add(sdf.format(CALENDAR.getTime()));
            CALENDAR.set(CALENDAR.DATE, CALENDAR.get(CALENDAR.DATE) - 2);//周一
            timeList.add(sdf.format(CALENDAR.getTime()));
            CALENDAR.set(CALENDAR.DATE, CALENDAR.get(CALENDAR.DATE) - 2);//周六
        }
        System.out.println("开奖次数：" + timeList.size());
        for (int i = 0; i < timeList.size(); i++) {
            System.out.print("\t" + timeList.get(i));
            if (i % 10 == 0 && i != 0) {
                System.out.print("\n");
            }
        }
    }
}
