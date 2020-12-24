package com.example.demo.utils;

public class NameRandom {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            System.out.println(randomName());
        }

    }

    private static String randomName() {
        int var = (int) (Math.random() * 5 + 7);   //循环次数
        String var1 = "";               //name 记录
        int i = 0;
        char c = 'a';
        while (i <= var) {
            c = i == 0 ? (char) ('A' + Math.random() * ('Z' - 'A' + 1)) : (char) ('a' + Math.random() * ('z' - 'a' + 1));
            //c = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
            var1 = var1 + c;
            i++;
        }
        return var1;
    }
}
