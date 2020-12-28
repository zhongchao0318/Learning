package com.example.demo.schedule;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.BallNumDao;
import com.example.demo.dao.CycleRecordDao;
import com.example.demo.dao.LuckBallDao;
import com.example.demo.dao.ReptileesDao;
import com.example.demo.dto.LuckBallSimpleDto;
import com.example.demo.entity.luck.BallNum;
import com.example.demo.entity.luck.CycleRecord;
import com.example.demo.entity.luck.LuckBall;
import com.example.demo.entity.luck.Reptiles;
import com.example.demo.service.luck.LuckBallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Value("${dlt.url}")
    private String dltUrl;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String numberStr = "";
    @Autowired
    LuckBallDao luckBallDao;
    @Autowired
    ReptileesDao reptileesDao;//原数据dao
    @Autowired
    CycleRecordDao recordDao;//爬取记录dao
    @Autowired
    BallNumDao ballNumDao;//统计结果dao

    @Autowired
    LuckBallService luckBallService;

    //    @PostConstruct
    private void getLuckBall() throws InterruptedException {//设置初始日期
        CycleRecord record = recordDao.findByStatus(1);//初始记录
        CycleRecord totalRecord = recordDao.findByStatus(3);//总计
        int year = record.getYear();
        int number = record.getNumber();//期数
        boolean flag = true;
        JSONObject body = new JSONObject();
        JSONObject param = new JSONObject();
        String data = "";          //存储返回的数据
        JSONObject dataJson = null;//返回文本转json
        JSONObject luckJson = null;//luck number 层级
        String luckNumberArr = "";//返回的数据字符串
        Reptiles reptiles = null;//原纪录
        String cycle = "";//期数
        LuckBall luckBall = null;
        while (flag) {
            data = "";
            cycle = year + "-" + getNumberStr(number);
            body.clear();
            param.clear();
            param.put("GameCode", "DLT");
            param.put("IssuseNumber", cycle);

            body.put("Param", param);
            logger.info(body.toString());
            data = HttpUtil.post(dltUrl, body.toJSONString());
            dataJson = JSON.parseObject(data);
            //发送请求
            if (null == dataJson.get("Value")) {//说明当前年份已经结束了
                logger.info("第{}不存在，年份和期数修改", cycle);
                number = 1;
                year++;
                logger.info("修改后》》》》年份：{}\t期数：{}", year, number);
            } else {
                luckJson = dataJson.getJSONObject("Value").getJSONObject("PreIssuseInfo");
                if ("".equals(luckJson.getString("WinNumber"))) {//空值，说明这一年的结束了，不晓得他为啥还要存个空值
                    logger.info("第{}不存在，年份和期数修改", cycle);
                    number = 1;
                    year++;
                    logger.info("修改后》》》》年份：{}\t期数：{}", year, number);
                } else {//处理正确返回的数据
                    //存储原数据
                    reptiles = new Reptiles();
                    reptiles.setCycle(cycle);
                    reptiles.setNumStr(luckJson.toString());
                    reptiles.setCreateTime(Calendar.getInstance().getTime());
                    reptiles.setModifyTime(Calendar.getInstance().getTime());
                    reptileesDao.saveAndFlush(reptiles);

                    luckNumberArr = luckJson.getString("WinNumber");
                    String[] arrayStr = luckNumberArr.split("\\|");
                    String[] redArr = arrayStr[0].split(",");
                    String[] blueArr = arrayStr[1].split(",");
                    luckBall = new LuckBall();
                    luckBall.setCycle(cycle);
                    luckBall.setRedOne(redArr[0]);
                    luckBall.setRedTwo(redArr[1]);
                    luckBall.setRedThree(redArr[2]);
                    luckBall.setRedFour(redArr[3]);
                    luckBall.setRedFive(redArr[4]);
                    luckBall.setBlueOne(blueArr[0]);
                    luckBall.setBlueTwo(blueArr[1]);
                    luckBall.setCreateTime(Calendar.getInstance().getTime());
                    luckBall.setModifyTime(Calendar.getInstance().getTime());
                    luckBallDao.saveAndFlush(luckBall);
                    number++;
                    totalRecord.setTotal(totalRecord.getTotal() + 1);
                    recordDao.saveAndFlush(totalRecord);
                    record.setNumber(number);
                    record.setYear(year);
                    recordDao.saveAndFlush(record);
                }
            }

            if (year > Calendar.getInstance().get(Calendar.YEAR)) {
                logger.info("结束查寻");
                flag = false;
            } else {
                Thread.sleep(10000);
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

    //    @Scheduled(cron = "0 0 23 * * Mon,Wed,Sat")
    private void getTodayLuckBall() {
        //https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=1&isVerify=1&pageNo=1&startTerm=20128&endTerm=20128
        logger.info("测试时间制定天执行：", sdf2.format(Calendar.getInstance().getTime()));
        logger.info("当前时间：{}", Calendar.getInstance().getTime());
        CycleRecord record = recordDao.findByStatus(0);//上一次获取记录
        int year = record.getYear();
        int number = record.getNumber();//期数
        boolean flag = true;
        JSONObject body = new JSONObject();
        JSONObject param = new JSONObject();
        String data = "";          //存储返回的数据
        JSONObject dataJson = null;//返回文本转json
        JSONObject luckJson = null;//luck number 层级
        String luckNumberArr = "";//返回的数据字符串
        Reptiles reptiles = null;//原纪录
        String cycle = "";//期数
        LuckBall luckBall = null;
        while (flag) {
            data = "";
            cycle = year + "-" + getNumberStr(number);
            body.clear();
            param.clear();
            param.put("GameCode", "DLT");
            param.put("IssuseNumber", cycle);

            body.put("Param", param);
            logger.info(body.toString());
            data = HttpUtil.post(dltUrl, body.toJSONString());
            dataJson = JSON.parseObject(data);
            //发送请求
            if (null == dataJson.get("Value")) {//说明当前年份已经结束了
                logger.info("第{}不存在，年份和期数修改", cycle);
                number = 1;
                year++;
                logger.info("修改后》》》》年份：{}\t期数：{}", year, number);
            } else {
                luckJson = dataJson.getJSONObject("Value").getJSONObject("PreIssuseInfo");
                if ("".equals(luckJson.getString("WinNumber"))) {//空值，说明这一年的结束了，不晓得他为啥还要存个空值
                    logger.info("第{}不存在，年份和期数修改", cycle);
                    number = 1;
                    year++;
                    logger.info("修改后》》》》年份：{}\t期数：{}", year, number);
                } else {//处理正确返回的数据
                    //存储原数据
                    reptiles = new Reptiles();
                    reptiles.setCycle(cycle);
                    reptiles.setNumStr(luckJson.toString());
                    reptiles.setCreateTime(Calendar.getInstance().getTime());
                    reptiles.setModifyTime(Calendar.getInstance().getTime());
                    reptileesDao.saveAndFlush(reptiles);

                    luckNumberArr = luckJson.getString("WinNumber");
                    String[] arrayStr = luckNumberArr.split("\\|");
                    String[] redArr = arrayStr[0].split(",");
                    String[] blueArr = arrayStr[1].split(",");
                    luckBall = new LuckBall();
                    luckBall.setCycle(cycle);
                    luckBall.setRedOne(redArr[0]);
                    luckBall.setRedTwo(redArr[1]);
                    luckBall.setRedThree(redArr[2]);
                    luckBall.setRedFour(redArr[3]);
                    luckBall.setRedFive(redArr[4]);
                    luckBall.setBlueOne(blueArr[0]);
                    luckBall.setBlueTwo(blueArr[1]);
                    luckBall.setCreateTime(Calendar.getInstance().getTime());
                    luckBall.setModifyTime(Calendar.getInstance().getTime());
                    logger.info("插入luckBall>>>>\t{}", luckBallDao.toString());
                    luckBallDao.saveAndFlush(luckBall);
                    number++;
                    //总记录条数修改
                    CycleRecord totalRecord = recordDao.findByStatus(3);
                    totalRecord.setTotal(totalRecord.getTotal() + 1);
                    recordDao.saveAndFlush(totalRecord);
                    //修改ballNum中的数据

                    flag = false;
                }
            }
            record.setNumber(number);
            record.setYear(year);
            recordDao.saveAndFlush(record);

        }
    }

    //@PostConstruct
    @Scheduled(cron = "0 30 21 * * Mon,Wed,Sat")
    private void getTodayDTL() throws InterruptedException {
        //https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=1&isVerify=1&pageNo=1&startTerm=20128&endTerm=20128
        String url = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry";
        logger.info("指定时间执行：{}", sdf2.format(Calendar.getInstance().getTime()));
        logger.info("当前时间：{}", Calendar.getInstance().getTime());
        CycleRecord record = recordDao.findByStatus(0);//上一次获取记录
        int year = record.getYear();
        int number = record.getNumber();//期数
        boolean flag = true;
        JSONObject param = new JSONObject();
        String data = "";          //存储返回的数据
        JSONObject dataJson = null;//返回文本转json
        JSONObject luckJson = null;//luck number 层级
        String luckNumberArr = "";//返回的数据字符串
        Reptiles reptiles = null;//原纪录
        String cycle = "";//期数
        LuckBall luckBall = null;
        while (flag) {
            cycle = String.valueOf(year).substring(2) + getNumberStr(number);
            param.put("gameNo", "85");
            param.put("provinceId", "0");
            param.put("pageSize", "1");
            param.put("pageNo", "1");
            param.put("pageNo", "1");
            param.put("startTerm", cycle);//开始期数
            param.put("endTerm", cycle);//结束期数
            logger.info("param:{}", param);
            data = HttpUtil.get(url, param);
            dataJson = JSON.parseObject(data);
            if (null == dataJson.get("value")) {
                logger.info("第{}不存在，年份和期数修改", cycle);
                number = 1;
                year++;
                logger.info("修改后》》》》年份：{}\t期数：{}", year, number);
            } else {
                if (dataJson.getJSONObject("value").getJSONArray("list").size() == 0) {
                    logger.info("第{}不存在，年份和期数修改", cycle);
                    number = 1;
                    year++;
                    logger.info("修改后》》》》年份：{}\t期数：{}", year, number);
                } else {
                    //处理正确返回的数据
                    luckJson = (JSONObject) dataJson.getJSONObject("value").getJSONArray("list").get(0);
                    luckJson.remove("prizeLevelList");
                    luckNumberArr = luckJson.getString("lotteryDrawResult");
                    logger.info("luckball:{}", luckNumberArr);
                    //存储原数据
                    reptiles = new Reptiles();
                    reptiles.setCycle(cycle);
                    reptiles.setNumStr(luckNumberArr);
                    reptiles.setCreateTime(Calendar.getInstance().getTime());
                    reptiles.setModifyTime(Calendar.getInstance().getTime());
                    reptileesDao.saveAndFlush(reptiles);

                    String[] arrayStr = luckNumberArr.split(" ");
                    luckBall = new LuckBall();
                    luckBall.setCycle(year + "-" + getNumberStr(number));
                    luckBall.setRedOne(arrayStr[0]);
                    luckBall.setRedTwo(arrayStr[1]);
                    luckBall.setRedThree(arrayStr[2]);
                    luckBall.setRedFour(arrayStr[3]);
                    luckBall.setRedFive(arrayStr[4]);
                    luckBall.setBlueOne(arrayStr[5]);
                    luckBall.setBlueTwo(arrayStr[6]);
                    luckBall.setCreateTime(getSysTime());
                    luckBall.setModifyTime(getSysTime());
                    logger.info(">>>>插入luckBall>>>>\t{}", luckBall.toString());
                    luckBallDao.saveAndFlush(luckBall);
                    number++;
                    //总记录条数修改
                    CycleRecord totalRecord = recordDao.findByStatus(3);
                    totalRecord.setTotal(totalRecord.getTotal() + 1);
                    logger.info(">>>>修改总记录：{}", totalRecord.toString());
                    recordDao.saveAndFlush(totalRecord);
                    flag = false;
                    //修改定时记录
                    record.setNumber(number);
                    record.setYear(year);
                    recordDao.saveAndFlush(record);
                    logger.info(">>>>定时抽取期数记录：{}", record.toString());
                    //修改ballNum中的统计数据
                    logger.info(">>>>修改ballNum年总记录数据");
                    updateByYear(year, luckBall);
                    logger.info(">>>>修改ballNum总记录数据");
                    updateTotals(luckBall);
                }
            }
            if (year > Calendar.getInstance().get(Calendar.YEAR)) {
                logger.info("年份{}\t超过当前年份，已结束", year);
                flag = false;
            } else {
                Thread.sleep(10000);
            }
        }
    }

    /**
     * 更新总计
     *
     * @param luckBall
     */
    private void updateTotals(LuckBall luckBall) {
        LuckBallSimpleDto dto = new LuckBallSimpleDto();
        BeanUtils.copyProperties(luckBall, dto);
        List<BallNum> redTotals = ballNumDao.findAllByYearAndStatus("total", 0);
        List<BallNum> blueTotals = ballNumDao.findAllByYearAndStatus("total", 1);
        for (BallNum ballNum : redTotals) {//red
            if (dto.getRedOne().equals(ballNum.getNumber())
                    || dto.getRedTwo().equals(ballNum.getNumber())
                    || dto.getRedThree().equals(ballNum.getNumber())
                    || dto.getRedFour().equals(ballNum.getNumber())
                    || dto.getRedFive().equals(ballNum.getNumber())
            ) {
                ballNum.setLuckCount(ballNum.getLuckCount() + 1);
            }
        }

        for (BallNum ballNum : blueTotals) {//bule
            if (dto.getBlueOne().equals(ballNum.getNumber()) || dto.getBlueTwo().equals(ballNum.getNumber())) {
                ballNum.setLuckCount(ballNum.getLuckCount() + 1);
            }
        }
        ballNumDao.saveAll(redTotals);
        ballNumDao.saveAll(blueTotals);
    }

    /**
     * 更新年记录
     */
    private void updateByYear(Integer year, LuckBall luckBall) {
        List<BallNum> redList = ballNumDao.findAllByYearAndStatus(year + "", 0);
        List<BallNum> blueList = ballNumDao.findAllByYearAndStatus(year + "", 1);
        if (redList.size() == 0 && blueList.size() == 0) {
            logger.info(">>>>{}年ballNum初始化>>>>", year);
            initBallNumberByYear(year);
        }
        List<BallNum> result = new ArrayList<>();
        for (BallNum ballNum : redList) {//red
            if (luckBall.getRedOne().equals(ballNum.getNumber())
                    || luckBall.getRedTwo().equals(ballNum.getNumber())
                    || luckBall.getRedThree().equals(ballNum.getNumber())
                    || luckBall.getRedFour().equals(ballNum.getNumber())
                    || luckBall.getRedFive().equals(ballNum.getNumber())
            ) {
                ballNum.setLuckCount(ballNum.getLuckCount() + 1);
            }
        }

        for (BallNum ballNum : blueList) {//bule
            if (luckBall.getBlueOne().equals(ballNum.getNumber()) || luckBall.getBlueTwo().equals(ballNum.getNumber())) {
                ballNum.setLuckCount(ballNum.getLuckCount() + 1);
            }
        }
        result.addAll(redList);
        result.addAll(blueList);
        ballNumDao.saveAll(result);
    }

    /**
     * 号码记录初始化
     *
     * @param year
     */
    private void initBallNumberByYear(Integer year) {
        BallNum ballNum = null;
        List<BallNum> list = new ArrayList<>();
        for (int j = 1; j < 36; j++) {//red
            ballNum = new BallNum();
            ballNum.setLuckCount(0);
            ballNum.setNumber(getNumberStr(j));
            ballNum.setStatus(0);
            ballNum.setYear(year + "");
            list.add(ballNum);
        }
        for (int j = 1; j < 13; j++) {
            ballNum = new BallNum();
            ballNum.setLuckCount(0);
            ballNum.setNumber(getNumberStr(j));
            ballNum.setStatus(1);
            ballNum.setYear(year + "");
            list.add(ballNum);
        }
        ballNumDao.saveAll(list);
    }

    private Date getSysTime() {
        return Calendar.getInstance().getTime();
    }
}
