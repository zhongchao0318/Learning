package com.example.demo.service.luck.impl;

import com.example.demo.dao.luck.LuckBallDao;
import com.example.demo.dto.LuckBallSimpleDto;
import com.example.demo.entity.luck.LuckBall;
import com.example.demo.service.luck.LuckBallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/16 10:01
 */
@Service
public class ILuckBallService implements LuckBallService {
    private static final Logger logger = LoggerFactory.getLogger(ILuckBallService.class);
    @Autowired
    LuckBallDao luckBallDao;

    /**
     * 年查询
     *
     * @param year
     * @return
     */
    @Override
    public List<LuckBallSimpleDto> findAllByYear(int year) {
        int end = year + 1;
        logger.info(">>>>查询>>>>{}年至{}年的数据>>>>>", year, end);
        List<LuckBallSimpleDto> list = findByCycleBetween(year + "", end + "");
        logger.info(">>>>{}年至{}年总计：{}期>>>>>", year, end, list.size());
        return list;
    }

    /**
     * 查寻全部
     *
     * @return
     */
    @Override
    public List<LuckBallSimpleDto> findAll() {
        List<LuckBallSimpleDto> list = realFindAll();
        logger.info(">>>>总记录条数：{}>>>>>", list.size());
        return list;
    }

    /**
     * 查寻全部dao
     *
     * @return
     */
    private List<LuckBallSimpleDto> realFindAll() {
        List<LuckBall> list = luckBallDao.findAll();
        List<LuckBallSimpleDto> result = getLuckBallSimpleDtos(list);
        return result;
    }

    /**
     * 查寻年数据
     *
     * @param start
     * @param end
     * @return
     */
    private List<LuckBallSimpleDto> findByCycleBetween(String start, String end) {
        List<LuckBall> list = luckBallDao.findAllByCycleBetween(start, end);
        List<LuckBallSimpleDto> result = getLuckBallSimpleDtos(list);
        return result;
    }

    private List<LuckBallSimpleDto> getLuckBallSimpleDtos(List<LuckBall> list) {
        List<LuckBallSimpleDto> result = new ArrayList<>();
        LuckBallSimpleDto simpleDto = null;
        for (LuckBall ball : list) {
            simpleDto = new LuckBallSimpleDto();
            BeanUtils.copyProperties(ball, simpleDto);
            result.add(simpleDto);
        }
        return result;
    }

}
