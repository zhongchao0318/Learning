package com.example.demo.entity.luck;

import com.example.demo.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: zc
 * @Date: 2020/12/1 13:28
 */
@Entity
@Table(name = "luck_ball")
@IdClass(BaseEntity.class)
@Data
public class LuckBall extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -629757853254968417L;
    @Column(name = "cycle", length = 50)
    private String cycle; //期数
    @Column(name = "red_one", length = 20)
    private String redOne;
    @Column(name = "red_two", length = 20)
    private String redTwo;
    @Column(name = "red_three", length = 20)
    private String redThree;
    @Column(name = "red_four", length = 20)
    private String redFour;
    @Column(name = "red_five", length = 20)
    private String redFive;
    @Column(name = "blue_one", length = 20)
    private String blueOne;
    @Column(name = "blue_two", length = 20)
    private String blueTwo;


}
