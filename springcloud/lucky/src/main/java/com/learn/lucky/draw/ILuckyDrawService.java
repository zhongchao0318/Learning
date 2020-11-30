package com.learn.lucky.draw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ILuckyDrawService {
    private static final Logger logger = LoggerFactory.getLogger(ILuckyDrawService.class);
    private static volatile List<String> luckyDate = new ArrayList<>();

    public String saveDate(List<String> date) {
        logger.info("---------date:{}", date.toString());
        luckyDate.addAll(date);
        return "ok";
    }

    public String getLuckyOne() {
        reorganize();
        return luckyDate.size() > 0 ? luckyDate.get(0) : null;
    }

    public String allDate() {
        return luckyDate.toString();
    }

    public String clearDate() {
        luckyDate.clear();
        return "ok";
    }

    /**
     * 洗牌
     */
    private static void reorganize() {
        logger.info("洗牌前：{}", luckyDate.toString());
        String param = "";  //临时存储
        Integer index;
        Random random = new Random();
        for (int i = luckyDate.size() - 1; i > 0; i--) {
            index = random.nextInt(i);
            param = luckyDate.get(index);
            luckyDate.set(index, luckyDate.get(i));
            luckyDate.set(i, param);
        }
        logger.info("洗牌后：{}", luckyDate.toString());
    }

}
