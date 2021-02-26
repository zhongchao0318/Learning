package com.example.demo.controller;

import com.example.demo.utils.RespConst;
import com.example.demo.utils.ReturnUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zc
 * @Date: 2021/2/24 9:19
 */
@RestController
@RequestMapping("doc")
public class DocController {
    private final Logger logger = LoggerFactory.getLogger(DocController.class);

    @PostMapping("upload")
    public String upload(@RequestBody MultipartFile[] files) {
        for (MultipartFile file : files) {
            logger.info(file.getOriginalFilename());
        }
        return ReturnUtil.result(RespConst.SUCCESS, "上传成功");
    }
}
