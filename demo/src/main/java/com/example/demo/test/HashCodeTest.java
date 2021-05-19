package com.example.demo.test;

import java.util.Objects;

/**
 * @Author: zc
 * @Date: 2021/3/22 15:49
 */
public class HashCodeTest {
    public static void main(String[] args) {
        TestObjec a = new TestObjec("小红","123",18);
        TestObjec b = new TestObjec("小红","123",18);
        System.out.println(a.hashCode()==b.hashCode());
        System.out.println(a.equals(b));
    }
}
















class TestObjec {
    private String name;
    private String idCard;
    private int age;

    public TestObjec() {

    }

    public TestObjec(String name, String idCard, int age) {
        this.name = name;
        this.idCard = idCard;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestObjec)) return false;
        TestObjec testObjec = (TestObjec) o;
        return getAge() == testObjec.getAge() &&
                getName().equals(testObjec.getName()) &&
                getIdCard().equals(testObjec.getIdCard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getIdCard(), getAge());
    }
}
