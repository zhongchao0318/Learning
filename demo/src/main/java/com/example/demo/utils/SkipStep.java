package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;


/**
 * newke
 */
@Slf4j
public class SkipStep {

    public static void main(String[] args) {
        int l = 1, r = 5, count = 0;
        int oldL = l, oldR = r;
        while (l % 3 != 1) {
            l++;
        }
        while (r % 3 != 1) {
            r--;
        }
        count = (r - l + 1) - ((r - l + 1) / 3 + 1);
        count += (oldR - r) + (l - oldL);
        System.out.println(count);
        System.out.println(divide(l, r));
    }

    public static int divide(int l, int r) {
        int count = 0;
        int intl = 0;
        StringBuilder sbl = new StringBuilder("");
        StringBuilder sbr = new StringBuilder("");
        for (int i = 1; i <= r; i++) {//1-5
            if (i < l) {//1-L
                sbl.append(i);
                System.out.println("l==" + sbl);
            }
            if (i >= l) {
                sbr = sbl;
                sbr.append(i);
                intl = Integer.parseInt(sbl.toString());
//                System.out.println("r==" + intl);
                if (intl % 3 == 0) {
                    count++;
                    System.out.println("r==" + intl);
                }
            }
        }
        return count;
    }

    public static int JumpFloor(int target) {
        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        int one = 1;
        int two = 2;
        int result = 0;
        for (int i = 2; i < target; i++) {
            result = one + two;
            one = two;
            two = result;
        }
        return result;
    }

    public static int jump(int target) {
        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        int befor = 2;
        int result = 0;
        for (int i = 2; i < target; i++) {
            result = befor * 2;
            befor = result;
        }
        return result;
    }

    public static int Fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int one = 1;
        int two = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = one + two;
            one = two;
            two = result;
        }
        return result;
    }

    public static int NumberOf1(int n) {
        int result = 0;
        String str = Integer.toBinaryString(n);
        char[] strList = str.toCharArray();
        for (int i = 0; i < strList.length; i++) {
            if (strList[i] == '1')
                result += 1;
        }
        return result;
    }

    /**
     * @param money
     * @param free  免税部分
     * @return
     */
    private static double getTaxRate(double money, double free, double aoi) {
//        double[] taxRate = {0.03, 0.1, 0.2, 0.25, 0.30, 0.35, 0.45};
        double[] taxRate = {0.45, 0.35, 0.30, 0.25, 0.2, 0.1, 0.03};
        double[] fastCount = {15160, 7160, 4410, 2660, 1410, 210, 0};
        double[] limits = {80000, 55000, 35000, 25000, 12000, 3000, 0};
        for (int i = 0; i < limits.length; i++) {
            if (money - free - aoi > limits[i]) {
                money = money - (money - free - aoi) * taxRate[i] - fastCount[i];
//                money = (money - free-aoi) * taxRate[i];
                break;
            }
        }
        return money;
    }

    /**
     * @param money xinzi
     * @param aoi   五险一金
     * @return
     */
    private static double getMoney(double money, double aoi) {
        //amount of insurance 五险一金
//        double aoi = 423.08;
//        double aoi = 492.16+240;
        double salary = money - aoi;
        //基本金额
        double free = 5000;
        return salary - free > 0 ? getTaxRate(money, free, aoi) : salary;
    }

    protected static int getLastStrSize(String content) {
        if (content.trim().length() <= 0) return 0;
        if (content.trim().length() > 5000) return -1;
        content = content.substring(content.lastIndexOf(" ") + 1);
        System.out.println("content:" + content);
        int count = 0;
        char[] str = content.toCharArray();
        for (char c : str
        ) {
            count += 1;
        }
        return count;
    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），
     * 每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public boolean Find(int target, int[][] array) {

        return false;
    }

    /**
     * 题面
     * 城市 A 新建了 n个座房子，
     * 城市规划处用 n-1 条双向街道将房子连在一起，
     * 使得任意两座房子之间有且仅有一条道路可达。
     * 牛牛和牛妹将被随机分到两个房子，现在牛牛想知道，
     * 他和牛妹房子的最长路径是多少。
     *
     * 输入
     * 第一行给出 n(1≤n≤100,000)。
     * 随后 3 行，每行n-1 整数。
     * 依次是房子 u , 房子 v , 街道长度 w。
     * 表示房子 u i与房子 v i 之间有一条长度为 w i的道路相连。
     * (1≤u,v≤n,u!=v,1≤w≤100,000) 。
     */
    /**
     * 返回最终结果
     *
     * @param n int整型 城市数量
     * @param u int整型一维数组
     * @param v int整型一维数组
     * @param w int整型一维数组
     * @return long长整型
     */
    private static long solve(int n, int[] u, int[] v, int[] w) {
        if (n < 1 || n > 100000) return -1;
        // write code here
        return 1;
    }
}
