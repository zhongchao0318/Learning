package com.example.demo.designpatterns.factory;


import lombok.Data;

@Data
public abstract class Animal {
    private String name;
    public Animal(String name){
        this.name = name;
    }
    public abstract void eat();

    public abstract void run();

    public abstract void jump();
}
