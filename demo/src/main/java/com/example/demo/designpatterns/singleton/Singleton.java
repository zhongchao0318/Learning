package com.example.demo.designpatterns.singleton;

public class Singleton {
    public static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
