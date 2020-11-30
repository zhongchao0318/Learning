package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.test.splitfeild.ObjectAttr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("{id}")
    public String pathParam(@PathVariable("id") String str) {
        return "传入参数" + str;
    }

    @GetMapping("check")
    public void test1(HttpServletRequest request, HttpServletResponse response) {
        String param = request.getParameter("param");
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject.toString());
    }

}
