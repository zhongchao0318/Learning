package com.example.demo.utils.threads;

public class TestA {
    private static String data = "";

    public static void main(String[] args) {
        Inner n = new Inner();
        n.run();
    }

    public void  go(String[][] a ){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    static class Inner implements Runnable{
        public static void go() {
            data = "zc";
            System.out.println(data);
        }

        @Override
        public void run() {

        }
    }
}
