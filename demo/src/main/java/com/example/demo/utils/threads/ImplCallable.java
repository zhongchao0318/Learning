package com.example.demo.utils.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ImplCallable implements Callable<Integer> {
    private static int count = 0;
    private Lock lock = new ReentrantLock();
    private int num;

    /*public ImplCallable() {
    }*/
    public ImplCallable(int num){
        this.num = num;
    }
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < this.num; i++) {
            lock.lock();
            try {
                count += 1;
                System.out.println(Thread.currentThread().getName() + "--count:\t" + count);
            } finally {
                lock.unlock();
            }
        }
        return count;
    }

}
