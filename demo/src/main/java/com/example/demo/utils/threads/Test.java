package com.example.demo.utils.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Callable<String> callable = new ImplCallable(2);
//        FutureTask<String> task = new FutureTask<String>(callable);
//        FutureTask<String> task1 = new FutureTask<String>(callable);
//        new Thread(task).start();
//        new Thread(task1).start();
//        System.out.println("task:" + task.get());
//        System.out.println("task:" + task1.get());

        Callable<String> callable = new UUIDUtilCallable(5000);
        FutureTask<String> task = new FutureTask<>(callable);
        FutureTask<String> task2 = new FutureTask<>(callable);
        FutureTask<String> task3 = new FutureTask<>(callable);
        FutureTask<String> task4 = new FutureTask<>(callable);
        FutureTask<String> task5 = new FutureTask<>(callable);
        FutureTask<String> task6 = new FutureTask<>(callable);
        FutureTask<String> task7 = new FutureTask<>(callable);
        FutureTask<String> task8 = new FutureTask<>(callable);
        FutureTask<String> task9 = new FutureTask<>(callable);
        FutureTask<String> task10 = new FutureTask<>(callable);

        new Thread(task).start();
        new Thread(task2).start();
        new Thread(task3).start();
        new Thread(task4).start();
        new Thread(task5).start();
        new Thread(task6).start();
        new Thread(task7).start();
        new Thread(task8).start();
        new Thread(task9).start();
        new Thread(task10).start();

        log.info("task:\t" + task.get());
        log.info("task2:\t" + task2.get());
        log.info("task3:\t" + task3.get());
        log.info("task4:\t" + task4.get());
        log.info("task5:\t" + task5.get());
        log.info("task6:\t" + task6.get());
        log.info("task7:\t" + task7.get());
        log.info("task8:\t" + task8.get());
        log.info("task9:\t" + task9.get());
        log.info("task10:\t" + task10.get());


    }
}
