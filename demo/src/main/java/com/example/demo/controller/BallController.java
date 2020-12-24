package com.example.demo.controller;

import com.example.demo.service.luck.BallNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zc
 * @Date: 2020/12/22 10:26
 */
@RestController
@RequestMapping("ball")
public class BallController {
    @Autowired
    BallNumberService ballNumberService;

    @GetMapping("download")
    public void download(HttpServletResponse response) {
        ballNumberService.download(response);
    }


}
