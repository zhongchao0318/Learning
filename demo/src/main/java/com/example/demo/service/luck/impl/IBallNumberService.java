package com.example.demo.service.luck.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.demo.dao.BallNumDao;
import com.example.demo.dto.BallNumDto;
import com.example.demo.entity.BallNum;
import com.example.demo.service.luck.BallNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/22 10:30
 */
@Service
public class IBallNumberService implements BallNumberService {
    private static final int START_YEAR = 2007;
    private static final Logger logger = LoggerFactory.getLogger(IBallNumberService.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
                    "attachment;filename=" + URLEncoder.encode(sdf.format(Calendar.getInstance().getTime()) + "统计表.xlsx", "UTF-8"));
            OutputStream out = response.getOutputStream();
            ExcelWriter writer = createExcelAll();
            writer.flush(out, true);
            writer.close();
            out.close();
        } catch (UnsupportedEncodingException e) {
            logger.info(" URLEncoder.encode 错误:{}", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("IO 异常:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    private ExcelWriter createExcelAll() {
        List<BallNumDto> redList = null;
        List<BallNumDto> blueList = null;
        //初始化时定义表名
        ExcelWriter writer = ExcelUtil.getWriter(true);
//        writer.setSheet("total");
        redList = findByYearAndStatus("total", 0);
        blueList = findByYearAndStatus("total", 1);
        writer.addHeaderAlias("number", "号码");
        writer.addHeaderAlias("luckCount", "出现次数");
        writer.addHeaderAlias("year", "年份");
        writer.write(redList, true);
        //跳过当前行
        writer.passCurrentRow();
        writer.addHeaderAlias("number", "号码");
        writer.addHeaderAlias("luckCount", "出现次数");
        writer.addHeaderAlias("year", "年份");
        writer.write(blueList, true);
        int lastYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = lastYear; i >= START_YEAR; i--) {
            writer.setSheet(i + "");
            writer.addHeaderAlias("number", "号码");
            writer.addHeaderAlias("luckCount", "出现次数");
            writer.addHeaderAlias("year", "年份");
            redList = findByYearAndStatus(i + "", 0);
            blueList = findByYearAndStatus(i + "", 1);
            writer.write(redList, true);
            //跳过当前行
            writer.passCurrentRow();
            writer.addHeaderAlias("number", "号码");
            writer.addHeaderAlias("luckCount", "出现次数");
            writer.addHeaderAlias("year", "年份");
            writer.write(blueList, true);
        }
        return writer;
    }

    private List<BallNumDto> findByYearAndStatus(String year, int status) {
        return getBallNumDtoUtil(findAllByYearAndStatus(year, status));
    }

    private List<BallNum> findAllByYearAndStatus(String year, int status) {
        return ballNumDao.findAllByYearAndStatusOrderByLuckCountDesc(year, status);
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
