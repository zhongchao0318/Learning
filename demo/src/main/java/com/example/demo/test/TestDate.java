package com.example.demo.test;


import java.util.Calendar;

/**
 * @Author: zc
 * @Date: 2021/2/1 17:33
 */
public class TestDate {
    public static void main(String[] args) {
        Calendar startDate1 = Calendar.getInstance();
        Calendar lastDate = Calendar.getInstance();
        Calendar nowDate = Calendar.getInstance();
        startDate1.set(2021, 0, 1);
        lastDate.set(2021, 1, 2);
        int startYear = startDate1.get(Calendar.YEAR);
        int startMonth = startDate1.get(Calendar.MONTH);
        int lastYear = lastDate.get(Calendar.YEAR);
        int lastMonth = nowDate.getTimeInMillis() > lastDate.getTimeInMillis() ? lastDate.get(Calendar.MONTH) : lastDate.get(Calendar.MONTH) - 1 < 0 ? 11 : lastDate.get(Calendar.MONTH) - 1;
//        if (startDate1.getTimeInMillis() < lastDate.getTimeInMillis()) {
//            if (startMonth == lastMonth && startYear == lastYear) {
//
//            } else if ((startMonth != lastMonth || startYear != lastYear) && startDate1.getTimeInMillis() < lastDate.getTimeInMillis()) {
//                System.out.println("已超时");
//            }
//        }

        if (startDate1.getTimeInMillis() < lastDate.getTimeInMillis()
                && (startMonth != lastMonth || startYear != lastYear)
        ) {
            System.out.println("已超时");
        }

    }
}
