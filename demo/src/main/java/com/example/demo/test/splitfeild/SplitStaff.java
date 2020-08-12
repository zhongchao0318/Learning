package com.example.demo.test.splitfeild;

import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SplitStaff {
    private static Logger logger = LoggerFactory.getLogger(SplitStaff.class);

    public static void main(String[] args) throws Exception {
        StaffSecret s = new StaffSecret();
        setStaffSecret(s);
        logger.info("" + splitField(s));
    }

    private static Object splitField(StaffSecret staffSecret) throws Exception {
        Map<String, String> fieldMap = new HashMap<>();//key：字段名  value：字段值
        Map<Integer, FieldAttr> objMap = new TreeMap<>();//key: 排序
        JSONArray jsonArray = new JSONArray();
        Class clazz = staffSecret.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String methodName;
        FieldAttr fieldAttr = null;
        for (int i = 0; i < fields.length; i++) {
            fieldAttr = new FieldAttr();
            methodName = "get" + fields[i].getName().substring(0, 1).toUpperCase()
                    + fields[i].getName().replaceFirst("\\w", "");
            Method getMethod = clazz.getDeclaredMethod(methodName);
            //打破封装
            getMethod.setAccessible(true);
            Object methodValue = getMethod.invoke(staffSecret);
            fieldMap.put(fields[i].getName(), String.valueOf(methodValue).trim());
            fieldAttr.setField(fields[i].getName());
            fieldAttr.setValue(String.valueOf(methodValue));
            objMap.put(i, fieldAttr);
            jsonArray.add(fieldAttr);

        }
        //JSON.toJSONString(objMap);
        logger.info(objMap.toString());
        return jsonArray;
    }

    /**
     * 设置值
     *
     * @param staffSecret
     */
    private static void setStaffSecret(StaffSecret staffSecret) {
        Class clazz = staffSecret.getClass();
        Field[] fields2 = clazz.getDeclaredFields();
        String methodName;
        String methodName2;
        for (int i = 0; i < fields2.length; i++) {
            //获取String变量
            if ("String".equals(fields2[i].getType().getSimpleName())) {
                try {
                    methodName = "get" + fields2[i].getName().substring(0, 1).toUpperCase()
                            + fields2[i].getName().replaceFirst("\\w", "");

                    methodName2 = "set" + fields2[i].getName().substring(0, 1).toUpperCase()
                            + fields2[i].getName().replaceFirst("\\w", "");
                    //log.info("员工表1方法:\t" + methodName + "\t员工表2方法:\t" + methodName2);
                    Method getMethod = clazz.getDeclaredMethod(methodName);
                    //打破封装
                    getMethod.setAccessible(true);
                    Object methodValue = getMethod.invoke(staffSecret);
                    methodValue = i + "field";
                    Method setMethod2 = clazz.getDeclaredMethod(methodName2, fields2[i].getType());
                    setMethod2.setAccessible(true);
                    setMethod2.invoke(staffSecret, String.valueOf(methodValue).trim());

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
