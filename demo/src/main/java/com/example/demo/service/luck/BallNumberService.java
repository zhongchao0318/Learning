package com.example.demo.service.luck;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @Author: zc
 * @Date: 2020/12/22 10:27
 */
public interface BallNumberService {
    void download(HttpServletResponse response) throws UnsupportedEncodingException;
}
