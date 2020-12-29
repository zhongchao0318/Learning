package com.example.demo.ball;

import com.example.demo.dao.luck.BallNumDao;
import com.example.demo.dto.LuckBallSimpleDto;
import com.example.demo.entity.luck.BallNum;
import com.example.demo.service.luck.LuckBallService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.*;

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
            ballNum.setLuckCount(0);
            ballNum.setNumber(getNumberStr(j));
            ballNum.setStatus(0);
            ballNum.setYear("total");
            list.add(ballNum);
        }
        for (int j = 1; j < 13; j++) {
            ballNum = new BallNum();
            ballNum.setLuckCount(0);
            ballNum.setNumber(getNumberStr(j));
            ballNum.setStatus(1);
            ballNum.setYear("total");
            list.add(ballNum);
        }

        for (int i = 2007; i < 2021; i++) {
            for (int j = 1; j < 36; j++) {//red
                ballNum = new BallNum();
                ballNum.setLuckCount(0);
                ballNum.setNumber(getNumberStr(j));
                ballNum.setStatus(0);
                ballNum.setYear(i + "");
                list.add(ballNum);
            }
            for (int j = 1; j < 13; j++) {
                ballNum = new BallNum();
                ballNum.setLuckCount(0);
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
                    ballNum.setLuckCount(ballNum.getLuckCount() + 1);
                }
            }

            for (BallNum ballNum : blueList) {//bule
                if (dto.getBlueOne().equals(ballNum.getNumber()) || dto.getBlueTwo().equals(ballNum.getNumber())) {
                    ballNum.setLuckCount(ballNum.getLuckCount() + 1);
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
                        ballNum.setLuckCount(ballNum.getLuckCount() + 1);
                    }
                }

                for (BallNum ballNum : blueList) {//bule
                    if (dto.getBlueOne().equals(ballNum.getNumber()) || dto.getBlueTwo().equals(ballNum.getNumber())) {
                        ballNum.setLuckCount(ballNum.getLuckCount() + 1);
                    }
                }
            }
            result.addAll(redList);
            result.addAll(blueList);
        }
        ballNumDao.saveAll(result);
    }

    @Test
    void repeat() {
        List<LuckBallSimpleDto> dtoList = luckBallService.findAll();
        Iterator iter = dtoList.iterator();
        while (iter.hasNext()) {
            LuckBallSimpleDto ballDto = (LuckBallSimpleDto) iter.next();
            for (LuckBallSimpleDto simpleDto : dtoList) {
                if (ballDto.getCycle().equals(simpleDto.getCycle())) {
                    continue;
                } else {
                    if (ballDto.getRedOne().equals(simpleDto.getRedOne())
                            && ballDto.getRedTwo().equals(simpleDto.getRedTwo())
                            && ballDto.getRedThree().equals(simpleDto.getRedThree())
                            && ballDto.getRedFour().equals(simpleDto.getRedFour())
                            && ballDto.getRedFive().equals(simpleDto.getRedFive())
                            && ballDto.getBlueOne().equals(simpleDto.getBlueOne())
                            && ballDto.getBlueTwo().equals(simpleDto.getBlueTwo())
                    ) {
                        logger.info(ballDto.toString());
                        logger.info(simpleDto.toString());
                    }
//                    if (ballDto.equals(simpleDto)) {
//                        logger.info(ballDto.toString());
//                        logger.info(simpleDto.toString());
//                    }
                }
            }
        }
    }


    /**
     * 总计校验
     */
//    @Test
    void checkCountBallByTotal() {
        List<BallNum> list = ballNumDao.findAllByYear("total");
        int i = 0;
        for (BallNum ballNum : list) {
            i = i + ballNum.getLuckCount();
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

    //期望
    @Test
    void mean() {

        for (int i = 2020; i >= 2007; i--) {
            List<LuckBallSimpleDto> dtoList = luckBallService.findAllByYear(i);
            List<Integer> rOne = new ArrayList<>();
            List<Integer> rTwo = new ArrayList<>();
            List<Integer> rThree = new ArrayList<>();
            List<Integer> rFour = new ArrayList<>();
            List<Integer> rFive = new ArrayList<>();
            List<Integer> bOne = new ArrayList<>();
            List<Integer> bTwo = new ArrayList<>();
            for (LuckBallSimpleDto simpleDto : dtoList) {
                rOne.add(Integer.valueOf(simpleDto.getRedOne()));
                rTwo.add(Integer.valueOf(simpleDto.getRedTwo()));
                rThree.add(Integer.valueOf(simpleDto.getRedThree()));
                rFour.add(Integer.valueOf(simpleDto.getRedFour()));
                rFive.add(Integer.valueOf(simpleDto.getRedFive()));
                bOne.add(Integer.valueOf(simpleDto.getBlueOne()));
                bTwo.add(Integer.valueOf(simpleDto.getBlueTwo()));
            }

            int one = getDataEx(rOne);
            int two = getDataEx(rTwo);
            int three = getDataEx(rThree);
            int four = getDataEx(rFour);
            int five = getDataEx(rFive);
            int one2 = getDataEx(bOne);
            int two2 = getDataEx(bTwo);
            logger.info("{}\t{} {} {} {} {}\t{} {}", i, one, two, three, four, five, one2, two2);
        }
        List<LuckBallSimpleDto> dtoList = luckBallService.findAll();
        List<Integer> rOne = new ArrayList<>();
        List<Integer> rTwo = new ArrayList<>();
        List<Integer> rThree = new ArrayList<>();
        List<Integer> rFour = new ArrayList<>();
        List<Integer> rFive = new ArrayList<>();
        List<Integer> bOne = new ArrayList<>();
        List<Integer> bTwo = new ArrayList<>();
        for (LuckBallSimpleDto simpleDto : dtoList) {
            rOne.add(Integer.valueOf(simpleDto.getRedOne()));
            rTwo.add(Integer.valueOf(simpleDto.getRedTwo()));
            rThree.add(Integer.valueOf(simpleDto.getRedThree()));
            rFour.add(Integer.valueOf(simpleDto.getRedFour()));
            rFive.add(Integer.valueOf(simpleDto.getRedFive()));
            bOne.add(Integer.valueOf(simpleDto.getBlueOne()));
            bTwo.add(Integer.valueOf(simpleDto.getBlueTwo()));
        }

        int one = getDataEx(rOne);
        int two = getDataEx(rTwo);
        int three = getDataEx(rThree);
        int four = getDataEx(rFour);
        int five = getDataEx(rFive);
        int one2 = getDataEx(bOne);
        int two2 = getDataEx(bTwo);
        logger.info("{}\t{} {} {} {} {}\t{} {}", "total", one, two, three, four, five, one2, two2);
    }

    @Test
    void meanByYear() {
        int i = 2020;
        List<LuckBallSimpleDto> dtoList = luckBallService.findAllByYear(i);
        List<Integer> rOne = new ArrayList<>();
        List<Integer> rTwo = new ArrayList<>();
        List<Integer> rThree = new ArrayList<>();
        List<Integer> rFour = new ArrayList<>();
        List<Integer> rFive = new ArrayList<>();
        List<Integer> bOne = new ArrayList<>();
        List<Integer> bTwo = new ArrayList<>();
        for (LuckBallSimpleDto simpleDto : dtoList) {
            rOne.add(Integer.valueOf(simpleDto.getRedOne()));
            rTwo.add(Integer.valueOf(simpleDto.getRedTwo()));
            rThree.add(Integer.valueOf(simpleDto.getRedThree()));
            rFour.add(Integer.valueOf(simpleDto.getRedFour()));
            rFive.add(Integer.valueOf(simpleDto.getRedFive()));
            bOne.add(Integer.valueOf(simpleDto.getBlueOne()));
            bTwo.add(Integer.valueOf(simpleDto.getBlueTwo()));
        }

        int one = getDataEx(rOne);
        int two = getDataEx(rTwo);
        int three = getDataEx(rThree);
        int four = getDataEx(rFour);
        int five = getDataEx(rFive);
        int one2 = getDataEx(bOne);
        int two2 = getDataEx(bTwo);
        logger.info("原\t{} {} {} {} {}\t{} {}", one, two, three, four, five, one2, two2);
        Set<String> res = new TreeSet<>();
        StringBuffer temp = null;
        flag_one:
        for (int j = 1; j < 32; j++) {
            if (j != 1) {
                rOne.remove(rOne.size() - 1);
            }
            rOne.add(j);
            one = getDataEx(rOne);
            flag_two:
            for (int k = j + 1; k < 33; k++) {
                if (k != 2) {
                    rTwo.remove(rTwo.size() - 1);
                }
                rTwo.add(k);
                two = getDataEx(rTwo);
                flag_three:
                for (int m = k + 1; m < 34; m++) {
                    if (m != 3) {
                        rThree.remove(rThree.size() - 1);
                    }
                    rThree.add(m);
                    three = getDataEx(rThree);
                    flag_four:
                    for (int n = m + 1; n < 35; n++) {
                        if (n != 4) {
                            rFour.remove(rFour.size() - 1);
                        }
                        rFour.add(n);
                        four = getDataEx(rFour);
                        flag_five:
                        for (int p = n + 1; p < 36; p++) {
                            if (p != 5) {
                                rFive.remove(rFive.size() - 1);
                            }
                            rFive.add(p);
                            five = getDataEx(rFive);
                            flag_b_one:
                            for (int q = 1; q < 12; q++) {
                                if (q != 1) {
                                    bOne.remove(bOne.remove(bOne.size() - 1));
                                }
                                bOne.add(q);
                                one2 = getDataEx(bOne);
                                for (int r = q + 1; r < 13; r++) {
                                    if (r != 2) {
                                        bTwo.remove(bTwo.size() - 1);
                                    }
                                    bTwo.add(r);
                                    two2 = getDataEx(bTwo);
                                    temp = new StringBuffer();
                                    temp.append(one).append(" ")
                                            .append(two).append(" ")
                                            .append(three).append(" ")
                                            .append(four).append(" ")
                                            .append(five).append(" ")
                                            .append(one2).append(" ")
                                            .append(two2).append(" ");
                                    res.add(temp.toString());
                                }
                            }
                        }
                    }
                }
            }

        }
        for (String str : res) {
            logger.info("改：{}", str);
        }
    }

    @Test
    void meanByYearTest() {
        int i = 2020;
        List<LuckBallSimpleDto> dtoList = luckBallService.findAllByYear(i);
        List<Integer> rOne = new ArrayList<>();
        List<Integer> rTwo = new ArrayList<>();
        List<Integer> rThree = new ArrayList<>();
        List<Integer> rFour = new ArrayList<>();
        List<Integer> rFive = new ArrayList<>();
        List<Integer> bOne = new ArrayList<>();
        List<Integer> bTwo = new ArrayList<>();
        for (LuckBallSimpleDto simpleDto : dtoList) {
            rOne.add(Integer.valueOf(simpleDto.getRedOne()));
            rTwo.add(Integer.valueOf(simpleDto.getRedTwo()));
            rThree.add(Integer.valueOf(simpleDto.getRedThree()));
            rFour.add(Integer.valueOf(simpleDto.getRedFour()));
            rFive.add(Integer.valueOf(simpleDto.getRedFive()));
            bOne.add(Integer.valueOf(simpleDto.getBlueOne()));
            bTwo.add(Integer.valueOf(simpleDto.getBlueTwo()));
        }

        int one = getDataEx(rOne);
        int two = getDataEx(rTwo);
        int three = getDataEx(rThree);
        int four = getDataEx(rFour);
        int five = getDataEx(rFive);
        int one2 = getDataEx(bOne);
        int two2 = getDataEx(bTwo);
        logger.info("原\t{} {} {} {} {}\t{} {}", one, two, three, four, five, one2, two2);
        Set<String> res = new TreeSet<>();
        Callable<Set<String>> callable = null;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Set<String>> completionService = new ExecutorCompletionService<>(
                executorService);
        flag_one:
        for (int j = 1; j < 32; j++) {
            if (j != 1) {
                rOne.remove(rOne.size() - 1);
            }
            rOne.add(j);
            one = getDataEx(rOne);
            callable = new ComposeCallable(j, one, rTwo, rThree, rFour, rFive, bOne, bTwo);
            completionService.submit(callable);
        }

        logger.info(">>>>上面执行完成>>>>");

        try {
            for (int j = 0; j < 31; j++) {
                res.addAll(completionService.take().get());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        logger.info(">>>>获取结果完成>>>>");
        for (String str : res) {
            logger.info("改：{}", str);
        }
    }

    @Test
    void meanByYearTest2() {
        int i = 2020;
        List<LuckBallSimpleDto> dtoList = luckBallService.findAllByYear(i);
        List<Integer> rOne = new ArrayList<>();
        List<Integer> rTwo = new ArrayList<>();
        List<Integer> rThree = new ArrayList<>();
        List<Integer> rFour = new ArrayList<>();
        List<Integer> rFive = new ArrayList<>();
        List<Integer> bOne = new ArrayList<>();
        List<Integer> bTwo = new ArrayList<>();
        for (LuckBallSimpleDto simpleDto : dtoList) {
            rOne.add(Integer.valueOf(simpleDto.getRedOne()));
            rTwo.add(Integer.valueOf(simpleDto.getRedTwo()));
            rThree.add(Integer.valueOf(simpleDto.getRedThree()));
            rFour.add(Integer.valueOf(simpleDto.getRedFour()));
            rFive.add(Integer.valueOf(simpleDto.getRedFive()));
            bOne.add(Integer.valueOf(simpleDto.getBlueOne()));
            bTwo.add(Integer.valueOf(simpleDto.getBlueTwo()));
        }

        int one = getDataEx(rOne);
        int two = getDataEx(rTwo);
        int three = getDataEx(rThree);
        int four = getDataEx(rFour);
        int five = getDataEx(rFive);
        int one2 = getDataEx(bOne);
        int two2 = getDataEx(bTwo);
        logger.info("原\t{} {} {} {} {}\t{} {}", one, two, three, four, five, one2, two2);
        Set<String> res = new TreeSet<>();
        Callable<Set<String>> callable = null;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Set<String>> completionService = new ExecutorCompletionService<>(
                executorService);
        int count = 0;
        flag_b_one:
        for (int q = 1; q < 12; q++) {
            if (q != 1) {
                bOne.remove(bOne.size() - 1);
            }
            bOne.add(q);
            one2 = getDataEx(bOne);
            for (int r = q + 1; r < 13; r++) {
                if (r != 2) {
                    bTwo.remove(bTwo.size() - 1);
                }
                bTwo.add(r);
                two2 = getDataEx(bTwo);
                callable = new ComposeBlueCallable(one2, two2, rOne, rTwo, rThree, rFour, rFive);
                completionService.submit(callable);
                count++;
            }
        }
        logger.info(">>>>上面执行完成>>>>{}", count);

        try {
            Set<String> tempSet = null;
            for (int z = 1; z < 12; z++) {
                for (int j = z + 1; j < 13; j++) {
                    tempSet = completionService.take().get();
                    for (String str : tempSet) {
                        res.add(str);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        logger.info(">>>>获取结果完成>>>>");
        for (String str : res) {
            logger.info("改：{}", str);
        }
    }

    //https://blog.csdn.net/u012264124/article/details/79621584
    private int getDataEx(List<Integer> mSourceData) {
        int num, sum, i, j;
        List<Integer> list = new ArrayList<>();
        for (i = 0; i < mSourceData.size(); i++) {
            num = mSourceData.get(i);
            if (list.size() == 0) {
                list.add(num);
                list.add(1);
            } else {
                for (j = 0; j < list.size(); j += 2) {
                    if (list.get(j) == num) {
                        list.set(j + 1, list.get(j + 1) + 1);
                        break;
                    }
                }
                if (j >= list.size()) {
                    list.add(num);
                    list.add(1);
                }
            }
        }
        i = mSourceData.size();
        float sum2 = 0f;
        for (j = 0; j < list.size(); j += 2) {
            sum2 += list.get(j) * list.get(j + 1) / (float) i;
        }
        sum = Math.round(sum2);
        return sum;
    }


}
