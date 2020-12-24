package com.example.demo.utils.threads;

import com.example.demo.utils.tree.Huanyuan;

import java.util.concurrent.Callable;

public class NumStrCallable implements Callable<String> {
    private Integer length = 0;
    public NumStrCallable(Integer length){
        this.length = length;
    }

    @Override
    public String call() throws Exception {
        return Huanyuan.getNumber(length);
    }
}
