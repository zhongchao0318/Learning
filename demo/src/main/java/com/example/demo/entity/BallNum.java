package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 号码出现次数
 *
 * @Author: zc
 * @Date: 2020/12/15 16:55
 */
@Data
@Entity
@Table(name = "ball_number")
public class BallNum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number")
    private String number;
    //0.red 1.blue
    @Column(name = "status")
    private Integer status;
    //出现次数
    @Column(name = "luck_count")
    private Integer luck_count;
    //每年的次数，如果是总出现次数，年份为total
    @Column(name = "year")
    private String year;

}
