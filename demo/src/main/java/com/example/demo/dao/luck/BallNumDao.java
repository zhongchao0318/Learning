package com.example.demo.dao.luck;

import com.example.demo.entity.luck.BallNum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/16 9:35
 */
public interface BallNumDao extends JpaRepository<BallNum, Integer> {
    BallNum findByNumberAndStatusAndYear(String number, Integer status, String year);

    List<BallNum> findAllByYear(String year);

    List<BallNum> findAllByYearAndStatus(String year, Integer status);

    List<BallNum> findAllByYearAndStatusOrderByLuckCountDesc(String year, Integer status);
}
