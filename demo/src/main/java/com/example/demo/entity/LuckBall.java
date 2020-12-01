package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: zc
 * @Date: 2020/12/1 13:28
 */
@Entity
@Table(name = "luck_ball")
public class LuckBall extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -629757853254968417L;
    @Column(name = "datatime", length = 50)
    private String datetime; //日期时间
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRedOne() {
        return redOne;
    }

    public void setRedOne(String redOne) {
        this.redOne = redOne;
    }

    public String getRedTwo() {
        return redTwo;
    }

    public void setRedTwo(String redTwo) {
        this.redTwo = redTwo;
    }

    public String getRedThree() {
        return redThree;
    }

    public void setRedThree(String redThree) {
        this.redThree = redThree;
    }

    public String getRedFour() {
        return redFour;
    }

    public void setRedFour(String redFour) {
        this.redFour = redFour;
    }

    public String getRedFive() {
        return redFive;
    }

    public void setRedFive(String redFive) {
        this.redFive = redFive;
    }

    public String getBlueOne() {
        return blueOne;
    }

    public void setBlueOne(String blueOne) {
        this.blueOne = blueOne;
    }

    public String getBlueTwo() {
        return blueTwo;
    }

    public void setBlueTwo(String blueTwo) {
        this.blueTwo = blueTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LuckBall)) return false;
        if (!super.equals(o)) return false;
        LuckBall luckBall = (LuckBall) o;
        return getDatetime().equals(luckBall.getDatetime()) &&
                getRedOne().equals(luckBall.getRedOne()) &&
                getRedTwo().equals(luckBall.getRedTwo()) &&
                getRedThree().equals(luckBall.getRedThree()) &&
                getRedFour().equals(luckBall.getRedFour()) &&
                getRedFive().equals(luckBall.getRedFive()) &&
                getBlueOne().equals(luckBall.getBlueOne()) &&
                getBlueTwo().equals(luckBall.getBlueTwo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDatetime(), getRedOne(), getRedTwo(), getRedThree(), getRedFour(), getRedFive(), getBlueOne(), getBlueTwo());
    }

    @Override
    public String toString() {
        return "LuckBall{" +
                "datetime='" + datetime + '\'' +
                ", redOne='" + redOne + '\'' +
                ", redTwo='" + redTwo + '\'' +
                ", redThree='" + redThree + '\'' +
                ", redFour='" + redFour + '\'' +
                ", redFive='" + redFive + '\'' +
                ", blueOne='" + blueOne + '\'' +
                ", blueTwo='" + blueTwo + '\'' +
                '}';
    }
}
