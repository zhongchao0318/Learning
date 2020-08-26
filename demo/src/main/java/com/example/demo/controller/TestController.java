package com.example.demo.controller;

import com.example.demo.test.splitfeild.ObjectAttr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public static void main(String[] args) {
        ObjectAttr obj = null;
        for (int i = 0; i < 100; i++) {
            obj = new ObjectAttr();
        }


        System.gc();
    }
}
