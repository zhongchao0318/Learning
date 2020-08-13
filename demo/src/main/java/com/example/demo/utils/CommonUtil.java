package com.example.demo.utils;

import com.example.demo.test.splitfeild.FieldAttr;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用工具
 */
public class CommonUtil {

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 将对象属性拆分成对象
     *
     * @param t
     * @param <T>
     * @return List<FieldAttr>
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

}
