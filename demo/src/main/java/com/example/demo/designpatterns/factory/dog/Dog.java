package com.example.demo.designpatterns.factory.dog;

import com.example.demo.designpatterns.factory.Animal;

public abstract class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(this.getName() + "吃狗粮");
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "快跑！");
    }

    @Override
    public void jump() {
        System.out.println(this.getName() + "跳了一下");
    }

    /**
     * 才能
     */
    public abstract void talent();
}
