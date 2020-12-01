package com.example.demo.dao;

import com.example.demo.entity.LuckBall;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zc
 * @Date: 2020/12/1 17:23
 */
public interface LuckBallDao extends JpaRepository<LuckBall, String> {
}
