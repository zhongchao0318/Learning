package com.example.demo.service.luck.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.demo.dao.BallNumDao;
import com.example.demo.dto.BallNumDto;
import com.example.demo.entity.BallNum;
import com.example.demo.service.luck.BallNumberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/22 10:30
 */
@Service
public class IBallNumberService implements BallNumberService {
    private static final int START_YEAR = 2007;
    @Autowired
    BallNumDao ballNumDao;

    @Override
    public void download(HttpServletResponse response) {
        try {
            // 配置文件下载
            // 清空response
            response.reset();
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // 下载文件能正常显示中文

            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("统计表", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String createExcelAll() {
        List<BallNumDto> redList = null;
        List<BallNumDto> blueList = null;
        //初始化时定义表名
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.setSheet("total");
        redList = findByYearAndStatus("total", 0);
        blueList = findByYearAndStatus("total", 1);
        return null;
    }

    private List<BallNumDto> findByYearAndStatus(String year, int status) {
        return getBallNumDtoUtil(findAllByYearAndStatus(year, status));
    }

    private List<BallNum> findAllByYearAndStatus(String year, int status) {
        return ballNumDao.findAllByYearAndStatus(year, status);
    }

    private List<BallNumDto> getBallNumDtoUtil(List<BallNum> ballNumList) {
        List<BallNumDto> list = new ArrayList<>();
        BallNumDto dto = null;
        for (BallNum ballNum : ballNumList) {
            dto = new BallNumDto();
            BeanUtils.copyProperties(ballNum, dto);
            list.add(dto);
        }
        return list;
    }
}
