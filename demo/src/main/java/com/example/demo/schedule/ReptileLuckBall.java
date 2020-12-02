package com.example.demo.schedule;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.LuckBallDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 定时获取number
 *
 * @Author: zc
 * @Date: 2020/12/1 14:02
 */
@EnableScheduling
@Component
public class ReptileLuckBall {
    private static Logger logger = LoggerFactory.getLogger(ReptileLuckBall.class);
    private static final String dltUrl = "https://api.xinti.com/chart/QueryPrizeDetailInfo";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Calendar CALENDAR = Calendar.getInstance();
    private String numberStr = "";
    @Autowired
    LuckBallDao luckBallDao;

    @PostConstruct
    private void setDate() throws InterruptedException {//设置初始日期
        int year = 2007;
        int number = 1;//期数
        boolean flag = true;
        JSONObject body = new JSONObject();
        JSONObject param = new JSONObject();
        String data = "";
        JSONObject dataJson = null;
        JSONObject luckJson = null;//luck number 层级
        while (flag) {
            data = "";
            body.clear();
            param.clear();
            param.put("GameCode", "DLT");
            param.put("IssuseNumber", year + "-" + getNumberStr(number));
            body.put("Param", param);
            logger.info(body.toString());
            data = HttpUtil.post(dltUrl, body);
            dataJson = JSON.parseObject(data);
            //发送请求
            if (null == dataJson.get("Value")) {
                number = 1;
                year++;
            } else {
                luckJson = dataJson.getJSONObject("Value").getJSONObject("PreIssuseInfo");
                if ("".equals(luckJson.getString("WinNumber"))) {//空值，说明这一年的结束了，不晓得他为啥还要存个空值
                    number = 1;
                    year++;
                } else {//处理正确返回的数据
                    String luckNumberArr = luckJson.getString("WinNumber");
                    //首先分开红蓝球
                    number++;
                }
            }
            Thread.sleep(1000);
            if (year == 2020 && number == 122) {
                flag = false;
            }
        }
    }

    private String getNumberStr(int number) {
        numberStr = "";
        if (number < 10) {
            numberStr = "00" + number;
        } else if (number < 100) {
            numberStr = "0" + number;
        } else {
            numberStr = "" + number;
        }
        return numberStr;
    }

    @Scheduled(cron = "0 0 23 * * Mon,Wed,Sat")
    private void getTodayLuckBall() {
        logger.info("测试时间制定天执行：", sdf2.format(Calendar.getInstance().getTime()));
        logger.info("当前时间：{}", Calendar.getInstance().getTime());
    }


}
