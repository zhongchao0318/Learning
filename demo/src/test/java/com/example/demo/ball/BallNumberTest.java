package com.example.demo.ball;

import com.example.demo.utils.threads.ComposeNumCallable;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author: zc
 * @Date: 2020/12/24 18:10
 */
public class BallNumberTest {
    public static void main(String[] args) {
        Set<String> res = new HashSet<>();
        Callable<Set<String>> callable = null;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Set<String>> completionService = new ExecutorCompletionService<>(
                executorService);
        int count = 0;
        for (int blue = 1; blue < 12; blue++) {
            for (int blue2 = blue + 1; blue2 < 13; blue2++) {
                callable = new ComposeNumCallable(blue, blue2);
                completionService.submit(callable);
                count += 1;
            }
        }

        try {
            for (int i = 0; i < count; i++) {
                res.addAll(completionService.take().get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("size:" + res.size());
    }
}
