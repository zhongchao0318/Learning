package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * 期数记录
 *
 * @Author: zc
 * @Date: 2020/12/15 14:18
 */
@Table(name = "cycle_record")
@Entity
@Data
public class CycleRecord {
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 年份
     */
    @Column(name = "year")
    private Integer year;
    /**
     * 期数
     */
    @Column(name = "number")
    private Integer number;
    /**
     * 状态（1.爬取期数开始记录  0.定时获取的期数记录  3.总开奖次数）
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 总计（status==3 ，赋值该值）
     */
    @Column(name = "total")
    private Integer total;

}
