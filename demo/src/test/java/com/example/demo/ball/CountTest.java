package com.example.demo.ball;

import com.example.demo.dao.BallNumDao;
import com.example.demo.dto.LuckBallSimpleDto;
import com.example.demo.entity.BallNum;
import com.example.demo.service.luck.LuckBallService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/16 10:39
 */
@SpringBootTest
public class CountTest {
    private static final Logger logger = LoggerFactory.getLogger(CountTest.class);
    @Autowired
    LuckBallService luckBallService;
    @Autowired
    BallNumDao ballNumDao;

    //初始化
    //@Test
    void initBallNum() {
        BallNum ballNum = null;
        List<BallNum> list = new ArrayList<>();

        for (int j = 1; j < 36; j++) {//red
            ballNum = new BallNum();
            ballNum.setLuck_count(0);
            ballNum.setNumber(getNumberStr(j));
            ballNum.setStatus(0);
            ballNum.setYear("total");
            list.add(ballNum);
        }
        for (int j = 1; j < 13; j++) {
            ballNum = new BallNum();
            ballNum.setLuck_count(0);
            ballNum.setNumber(getNumberStr(j));
            ballNum.setStatus(1);
            ballNum.setYear("total");
            list.add(ballNum);
        }

        for (int i = 2007; i < 2021; i++) {
            for (int j = 1; j < 36; j++) {//red
                ballNum = new BallNum();
                ballNum.setLuck_count(0);
                ballNum.setNumber(getNumberStr(j));
                ballNum.setStatus(0);
                ballNum.setYear(i + "");
                list.add(ballNum);
            }
            for (int j = 1; j < 13; j++) {
                ballNum = new BallNum();
                ballNum.setLuck_count(0);
                ballNum.setNumber(getNumberStr(j));
                ballNum.setStatus(1);
                ballNum.setYear(i + "");
                list.add(ballNum);
            }
        }
        ballNumDao.saveAll(list);
    }

    /**
     * 总计
     */
    @Test
    void countBallByTotal() {
        List<LuckBallSimpleDto> luckList = luckBallService.findAll();
        List<BallNum> redList = ballNumDao.findAllByYearAndStatus("total", 0);
        List<BallNum> blueList = ballNumDao.findAllByYearAndStatus("total", 1);
        for (LuckBallSimpleDto dto : luckList) {
            logger.info(dto.toString());
            for (BallNum ballNum : redList) {//red
                if (dto.getRedOne().equals(ballNum.getNumber())
                        || dto.getRedTwo().equals(ballNum.getNumber())
                        || dto.getRedThree().equals(ballNum.getNumber())
                        || dto.getRedFour().equals(ballNum.getNumber())
                        || dto.getRedFive().equals(ballNum.getNumber())
                ) {
                    ballNum.setLuck_count(ballNum.getLuck_count() + 1);
                }
            }

            for (BallNum ballNum : blueList) {//bule
                if (dto.getBlueOne().equals(ballNum.getNumber()) || dto.getBlueTwo().equals(ballNum.getNumber())) {
                    ballNum.setLuck_count(ballNum.getLuck_count() + 1);
                }
            }
        }
        ballNumDao.saveAll(redList);
        ballNumDao.saveAll(blueList);
        countBallByYear();
    }

    /**
     * 统计每年
     */
    @Test
    void countBallByYear() {
        List<LuckBallSimpleDto> dtoList = null;
        List<BallNum> redList = null;
        List<BallNum> blueList = null;
        List<BallNum> result = new ArrayList<>();
        for (int i = 2007; i < 2021; i++) {
            dtoList = luckBallService.findAllByYear(i);
            redList = ballNumDao.findAllByYearAndStatus(i + "", 0);
            blueList = ballNumDao.findAllByYearAndStatus(i + "", 1);
            for (LuckBallSimpleDto dto : dtoList) {
                logger.info(dto.toString());
                for (BallNum ballNum : redList) {//red
                    if (dto.getRedOne().equals(ballNum.getNumber())
                            || dto.getRedTwo().equals(ballNum.getNumber())
                            || dto.getRedThree().equals(ballNum.getNumber())
                            || dto.getRedFour().equals(ballNum.getNumber())
                            || dto.getRedFive().equals(ballNum.getNumber())
                    ) {
                        ballNum.setLuck_count(ballNum.getLuck_count() + 1);
                    }
                }

                for (BallNum ballNum : blueList) {//bule
                    if (dto.getBlueOne().equals(ballNum.getNumber()) || dto.getBlueTwo().equals(ballNum.getNumber())) {
                        ballNum.setLuck_count(ballNum.getLuck_count() + 1);
                    }
                }
            }
            result.addAll(redList);
            result.addAll(blueList);
        }
        ballNumDao.saveAll(result);
    }

    /**
     * 总计校验
     */
//    @Test
    void checkCountBallByTotal() {
        List<BallNum> list = ballNumDao.findAllByYear("total");
        int i = 0;
        for (BallNum ballNum : list) {
            i = i + ballNum.getLuck_count();
        }
        logger.info("i:{}", i);
    }


    private String getNumberStr(int number) {
        String numberStr = "";
        if (number < 10) {
            numberStr = "0" + number;
        } else if (number < 100) {
            numberStr = "" + number;
        } else {
            numberStr = "" + number;
        }
        return numberStr;
    }
}
