package com.example.demo.entity.luck;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 所有组合
 * @Author: zc
 * @Date: 2020/12/28 11:18
 */
@Data
@Entity
@Table(name = "compose")
public class Compose implements Serializable {
    private static final long serialVersionUID = 6681672626050691769L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

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
