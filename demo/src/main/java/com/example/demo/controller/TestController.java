package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
