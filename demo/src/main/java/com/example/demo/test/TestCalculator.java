package com.example.demo.test;

/**
 * 二进制实现计算器
 *
 * @Author: zc
 * @Date: 2021/1/29 15:23
 */
public class TestCalculator {
    public static void main(String[] args) {
        int n = 4;
//        for (int i = 0; i < 20; i++) {
//            System.out.println(i+"\t\t"+(i + n) + "\t\t\t" + add(i, n));
//        }
        System.out.println(add(12, 4));

    }

    public static int add(int a, int b) {
        // 得到原位和
        int xor = a ^ b;
        int forWoad = (a & b) << 1;
        return forWoad == 0 ? xor : add(xor, forWoad);
    }

    public static Integer sub(int num1, int num2) {
        return null;
    }

    //异或运算
    public static int myXOR(int num1, int num2) {
        return num1 ^ num2;
    }

    //与运算
    public static int myAND(int num1, int num2) {
        return num1 & num2;
    }

}
