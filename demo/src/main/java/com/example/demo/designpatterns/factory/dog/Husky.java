package com.example.demo.designpatterns.factory.dog;

public class Husky extends Dog {
    public Husky(String name) {
        super(name);
    }

    @Override
    public void talent() {
        System.out.println("哈士奇-" + this.getName() + "开始拆家了");
    }
}
