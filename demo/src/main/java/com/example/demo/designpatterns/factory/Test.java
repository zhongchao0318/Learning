package com.example.demo.designpatterns.factory;

import com.example.demo.designpatterns.factory.dog.*;

public class Test {
    public static void main(String[] args) {
        DogFactory dog = new DogTalent();
        Husky h = dog.createHushy("小二");
        h.talent();
        h.jump();
        Samoye s = dog.createSamoye("小美");
        s.talent();
        s.run();
    }
}
