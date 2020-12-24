package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReturnUtil implements Serializable {
    private static final long serialVersionUID = -3643698585704483093L;
    /**
     * 接口状态码
     */
    private static final String API_CODE = "code";
    //接口提示信息
    private static final String API_MESSAGE = "message";
    //数据
    private static final String API_DATA = "data";
    //其他数据
    private static final String API_DATA_OTHER = "other";
    //当前页码
    private static final String API_DATA_PAGE = "page";
    //每页条目数量
    private static final String API_DATA_PAGE_SIZE = "size";
    //总条目数
    private static final String API_DATA_TOTAL = "total";

    private static ObjectMapper jackson = new ObjectMapper();

    public static void suc(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE );
        response.getWriter().write(ReturnUtil.suc());
    }

    public static void fail(HttpServletResponse response, int code, String message) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(fail(code, message));
    }

    public static String fail(int code, String message) {
        return result(code, message);
    }

    public static String suc() {
        return result(RespConst.SUCCESS);
    }

    private static String result(Integer code) {
        return result(code, null, null, null, null, null, null);
    }

    public static String result(Integer code, String message) {
        return result(code, message, null, null, null, null, null);
    }

    public static String result(Integer code, String message, Object data) {
        return result(code, message, data, null, null, null, null);
    }

    public static String result(Integer code, String message, Object data, Object other) {
        return result(code, message, data, other, null, null, null);
    }

    public static String resule(Integer code, String message, Object data, Integer page, Integer size, Integer total) {
        return result(code, message, data, null, page, size, total);
    }

    public static <T> String result(int code, String message, T data, T other, Integer page, Integer size, Integer total) {
        Map<String, Object> dataMap = new HashMap<>(7);
        dataMap.put(API_CODE, code);
        if (!StringUtils.isEmpty(message))
            dataMap.put(API_MESSAGE, message);
        if (!StringUtils.isEmpty(data))
            dataMap.put(API_DATA, data);
        if (!StringUtils.isEmpty(other))
            dataMap.put(API_DATA_OTHER, other);
        if (!StringUtils.isEmpty(page))
            dataMap.put(API_DATA_PAGE, page);
        if (!StringUtils.isEmpty(size))
            dataMap.put(API_DATA_PAGE_SIZE, size);
        if (!StringUtils.isEmpty(total))
            dataMap.put(API_DATA_TOTAL, total);
        try {
            return jackson.writeValueAsString(dataMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
