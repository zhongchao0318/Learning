package com.example.demo.utils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectReflect<T> {
    private static final String GET = "get";
    private static final String SET = "set";

    public <T> Object setObjectReflect(T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<T> clazz = (Class<T>) t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String getMethodName, setMethodName, attrName;  //get,set,attribute
        for (int i = 0; i < fields.length; i++) {
            attrName = fields[i].getType().getSimpleName();
            getMethodName = GET + fields[i].getName().substring(0, 1).toUpperCase()
                    + fields[i].getName().replaceFirst("\\w", "");
            System.out.println(getMethodName);
            Method getMethod = clazz.getDeclaredMethod(getMethodName);
            //打破封装
            getMethod.setAccessible(true);
            Object methodValue = getMethod.invoke(t);
            if (methodValue != null) {
                if("String".equals(attrName)) {
                    methodValue = methodValue + "123";
                }
                setMethodName = SET + fields[i].getName().substring(0, 1).toUpperCase()
                        + fields[i].getName().replaceFirst("\\w", "");
                Method setMethod = clazz.getDeclaredMethod(setMethodName, fields[i].getType());
                setMethod.setAccessible(true);
                setMethod.invoke(t, methodValue);
            }

        }
        return t;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        MenuInfo info = new MenuInfo();
//        info.setMname("ceshi");
//        info.setType("1");
//        ObjectReflect<MenuInfo> ob = new ObjectReflect();
//
//        MenuInfo info1 = (MenuInfo) ob.<MenuInfo>setObjectReflect(info);
//        System.out.println(info1.toString());

    }
}
