package com.example.demo.designpatterns.factory.cat;

import com.example.demo.designpatterns.factory.Animal;

public abstract class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(this.getName() + "吃猫粮");
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "快跑！");
    }

    @Override
    public void jump() {
        System.out.println(this.getName() + "跳了一下");
    }

    public abstract void grab();
}
