package com.example.demo.schedule;

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

    @Autowired
    LuckBallDao luckBallDao;

    @PostConstruct
    private void setDate() {//设置初始日期
        CALENDAR.set(Calendar.MONTH, 10);//设置月
        CALENDAR.set(Calendar.DATE, 28);//设置日期
        List<String> timeList = new ArrayList<>();
        for (int i = 0; i < (52 * 4) - 4; i++) {
            timeList.add(sdf.format(CALENDAR.getTime()));
            CALENDAR.set(CALENDAR.DATE, CALENDAR.get(CALENDAR.DATE) - 3);//周三
            timeList.add(sdf.format(CALENDAR.getTime()));
            CALENDAR.set(CALENDAR.DATE, CALENDAR.get(CALENDAR.DATE) - 2);//周一
            timeList.add(sdf.format(CALENDAR.getTime()));
            CALENDAR.set(CALENDAR.DATE, CALENDAR.get(CALENDAR.DATE) - 2);//周六
        }
        System.out.println("开奖次数：" + timeList.size());
        String[] dateArr = null;
        for (String str : timeList) {
//            dateArr=str.split();
        }
    }

    @Scheduled(cron = "0 0 23 * * Mon,Wed,Sat")
    private void getTodayLuckBall() {
        logger.info("测试时间制定天执行：", sdf2.format(Calendar.getInstance().getTime()));
        logger.info("当前时间：{}", Calendar.getInstance().getTime());
    }


}
