package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zc
 * @Date: 2021/1/14 17:44
 */
@Component
public class MyThreadCoolUtils {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static ExecutorService getExecutor() {
        return executorService;
    }
}
