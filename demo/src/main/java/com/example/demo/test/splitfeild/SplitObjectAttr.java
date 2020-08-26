package com.example.demo.test.splitfeild;

import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class SplitObjectAttr {
    private static Logger logger = LoggerFactory.getLogger(SplitObjectAttr.class);

    public static void main(String[] args) throws Exception {
        ObjectAttr s = new ObjectAttr();
        setStaffSecret(s);
        logger.info(s.toString());
        logger.info("" + splitField(s));
        JSONArray array = new JSONArray();
        array.addAll(splitField2(s));
        logger.info("" + array);
    }

    /**
     * 将对象属性拆分成对象
     *
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    private static <T> List<FieldAttr> splitField2(T t) throws Exception {
        List<FieldAttr> list = new ArrayList<>();
        Class clazz = t.getClass();
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
            Object methodValue = getMethod.invoke(t);

            fieldAttr.setField(fields[i].getName());
            fieldAttr.setValue(String.valueOf(methodValue));
            list.add(fieldAttr);

        }
        return list;
    }

    /**
     * 将对象属性拆分成对象
     *
     * @param objectAttr
     * @return
     * @throws Exception
     */
    @Deprecated
    private static Object splitField(ObjectAttr objectAttr) throws Exception {
        Map<String, String> fieldMap = new HashMap<>();//key：字段名  value：字段值
        Map<Integer, FieldAttr> objMap = new TreeMap<>();//key: 排序
        JSONArray jsonArray = new JSONArray();
        Class clazz = objectAttr.getClass();
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
            Object methodValue = getMethod.invoke(objectAttr);
            fieldMap.put(fields[i].getName(), String.valueOf(methodValue).trim());
            fieldAttr.setField(fields[i].getName());
            fieldAttr.setValue(String.valueOf(methodValue));
            objMap.put(i, fieldAttr);
            jsonArray.add(fieldAttr);

        }
        //JSON.toJSONString(objMap);
        return jsonArray;
    }

    /**
     * 设置值
     *
     * @param objectAttr
     */
    private static void setStaffSecret(ObjectAttr objectAttr) {
        Class clazz = objectAttr.getClass();
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
                    Object methodValue = getMethod.invoke(objectAttr);
                    methodValue = i + "field";
                    Method setMethod2 = clazz.getDeclaredMethod(methodName2, fields2[i].getType());
                    setMethod2.setAccessible(true);
                    setMethod2.invoke(objectAttr, String.valueOf(methodValue).trim());

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
