package com.example.demo.utils;

import java.util.*;

public class MapUtil {

    /**
     * 按Map value值降序
     *
     * @param data
     * @return
     */
    public static List<Map.Entry<String, String>> getValueDesc(Map<String, String> data) {
        List<Map.Entry<String, String>> list = new ArrayList<>(data.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o2.getValue().compareTo(o1.getValue()); /*升序改为 o1.getValue().compareTo(o2.getValue())*/
            }
        });
        return list;
    }

    /**
     * 按Map value值升序
     *
     * @param data
     * @return
     */
    public static List<Map.Entry<String, String>> getValueAsc(Map<String, String> data) {
        List<Map.Entry<String, String>> list = new ArrayList<>(data.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue()); /*升序改为 o1.getValue().compareTo(o2.getValue())*/
            }
        });
        return list;
    }

    public static <T> List<T> copy(List<T> i1) {
        List<T> i2 = new ArrayList<>();
        for(T t : i1) {
            i2.add(t);
        }
        return i2;
    }

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        List<String> b = copy(a);
        a.add("a");
        a.add("b");
        b.add("c");
        a.add("d");
        for (String key : a) {
            System.out.println(key);
        }
        System.out.println("-----------------");
        for (String key : b) {
            System.out.println(key);
        }
    }
}
