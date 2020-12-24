package com.example.demo.utils.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class UUIDUtilCallable implements Callable<String> {
    private Integer num;
    private static final Set set = new HashSet();
    private static final List<String> result = new ArrayList<>();
    private Lock lock = new ReentrantLock();

    public UUIDUtilCallable(int num) {
        this.num = num;
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private Integer getUUIDInOrderId() {
        Integer orderId = 0;
        lock.lock();
        try {
            orderId = UUID.randomUUID().toString().hashCode();
            orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        } finally {
            lock.unlock();
        }
        // String orderId = UUID.randomUUID().toString().hashCode();
        return orderId;
    }

    @Override
    public String call() throws Exception {
        for (int i = 0; i < num; i++) {
//            int tempnum = getUUIDInOrderId();
            String tempnum = getUUID();
            set.add(tempnum);
            lock.lock();
            try{
                if(result.contains(tempnum)){
                    log.info("已存在:\t"+ tempnum+"\t位置"+result.indexOf(tempnum));
                }else{
                    result.add(tempnum);
                }
            }finally {
                lock.unlock();
            }

        }
        return " "+set.size();
    }

    public static void main(String[] args) {

    }
}
