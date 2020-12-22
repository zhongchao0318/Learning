package com.example.demo.dao;

import com.example.demo.entity.CycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zc
 * @Date: 2020/12/15 14:25
 */
public interface CycleRecordDao extends JpaRepository<CycleRecord, Integer> {
    /**
     * 查询期数
     * @param status 1 or 0
     * @return
     */
    CycleRecord findByStatus(Integer status);
}