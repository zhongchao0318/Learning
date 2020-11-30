package com.example.demo.designpatterns.factory.dog;

public class Samoye extends Dog {
    public Samoye(String name) {
        super(name);
    }

    @Override
    public void talent() {
        System.out.println("萨摩耶-" + this.getName() + "微微一笑");
    }
}
