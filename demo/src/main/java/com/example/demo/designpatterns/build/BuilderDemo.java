package com.example.demo.designpatterns.build;

import lombok.Data;

/**
 * 建造者模式
 */
public class BuilderDemo {
    public static People builder() {
        return new People();
    }

    public static void main(String[] args) {
        BuilderDemo.builder().setPeopleName("小美").setPeopleAge(18).build();
    }
}

@Data
class People {
    private String name;
    private int age;

    public People setPeopleName(String name) {
        this.setName(name);
        return this;
    }

    public People setPeopleAge(int age) {
        this.setAge(age);
        return this;
    }

    public void build() {
        System.out.println(this.toString());
    }
}
