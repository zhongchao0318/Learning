package com.example.demo.dto;

import lombok.Data;

/**
 * 号码出现次数dto
 *
 * @Author: zc
 * @Date: 2020/12/22 10:53
 */
@Data
public class BallNumDto {
    //    private Integer id;
    private String number;
    //0.red 1.blue
//    private Integer status;
    //出现次数
    private Integer luckCount;
    //每年的次数，如果是总出现次数，年份为total
    private String year;
}
