package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 抓取数据原值
 *
 * @Author: zc
 * @Date: 2020/12/3 17:10
 */
@Entity
@Table(name = "reptiles_log")
@Data
@IdClass(BaseEntity.class)
public class Reptiles extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3178196937405143039L;

    @Column(name = "cycle", length = 50)
    private String cycle; //期数

    @Column(name = "number_str", length = 3000)
    private String numStr;

}
