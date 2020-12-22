package com.example.demo.dao;

import com.example.demo.entity.LuckBall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/1 17:23
 */
public interface LuckBallDao extends JpaRepository<LuckBall, String> {
    List<LuckBall> findAllByCycleBetween(String startCycle, String endCycle);
}
