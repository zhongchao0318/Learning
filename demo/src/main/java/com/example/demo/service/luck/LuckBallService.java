package com.example.demo.service.luck;

import com.example.demo.dto.LuckBallSimpleDto;

import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/16 10:01
 */
public interface LuckBallService {
    List<LuckBallSimpleDto> findAllByYear(int year);

    List<LuckBallSimpleDto> findAll();
}
