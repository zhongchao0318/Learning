package com.example.demo.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.test.ecology.params.FieldString;
import com.example.demo.utils.RespConst;
import com.example.demo.utils.ReturnUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: zc
 * @Date: 2021/2/24 9:19
 */
@RestController
@RequestMapping("doc")
public class DocController {
    private final Logger logger = LoggerFactory.getLogger(DocController.class);
    private final String oaurl = "http://localhost:11112/oaapi/doc/uploadFile";

    @PostMapping("upload")
    public String upload(@RequestBody MultipartFile[] files, @RequestParam String loginid) {
        logger.info("loginid:{}", loginid);
        try {
            for (MultipartFile file : files) {
                logger.info(file.getOriginalFilename());

                String fileJson = HttpRequest.post(oaurl)
                        .timeout(60000)
                        .contentType("multipart/form-data")
                        .form("userid", loginid)
                        .form("name", file.getOriginalFilename())
                        .form("file", fileToString(file.getInputStream()))
                        .execute().body();
                logger.info("文件上传结果：{}", fileJson);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        JSONObject workflowJson = new JSONObject();//流程参数主体

        JSONObject mainDataJson = new JSONObject();
        JSONArray mainDataArrJson = new JSONArray();
        mainDataJson.put("root", mainDataArrJson);
        FieldString fieldParams = new FieldString();
        fieldParams.setFieldName("employid");
        fieldParams.setFieldValue(loginid);
        mainDataArrJson.add(fieldParams);
        fieldParams = new FieldString();
        fieldParams.setFieldName("annex");
        fieldParams.setFieldValue("");
        mainDataArrJson.add(fieldParams);

//        workflowJson.put("detailData","");//明细表数据
        workflowJson.put("mainData", mainDataJson);//主表数据
//        workflowJson.put("otherParams", "");//其他参数
//        workflowJson.put("remark", "");//签字意见
//        workflowJson.put("requestLevel","");//紧急程度
        workflowJson.put("requestName", "RestFul创建流程测试");//流程标题
        workflowJson.put("workflowId", "25521");//流程id

        JSONObject params = new JSONObject();
        params.put("workflow", workflowJson);//流程主体
        params.put("creator", loginid);//创建人工号
        logger.info(params.toString());
        return ReturnUtil.result(RespConst.SUCCESS, "上传成功");
    }

    private String fileToString(InputStream inputStream) throws IOException {
        StringBuffer sb = new StringBuffer();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
