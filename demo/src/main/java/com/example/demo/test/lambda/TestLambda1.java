package com.example.demo.test.lambda;

/**
 * @Author: zc
 * @Date: 2021/1/27 14:49
 */
public class TestLambda1 {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println("hello");
        };
        r.run();
    }
}


