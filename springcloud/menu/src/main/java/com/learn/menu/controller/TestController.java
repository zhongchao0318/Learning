package com.learn.menu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("actuator")
public class TestController {
    @GetMapping("info")
    public String getInfo(HttpServletRequest request) {
        return "test:\t" + request.getLocalAddr();
    }
}
