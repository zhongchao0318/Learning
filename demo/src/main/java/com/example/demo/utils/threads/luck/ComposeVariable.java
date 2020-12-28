package com.example.demo.utils.threads.luck;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 组合成员变量
 *
 * @Author: zc
 * @Date: 2020/12/25 10:02
 */
public class ComposeVariable<T> implements Serializable {
    private static final long serialVersionUID = -7054270411654643562L;
    public List<T> res = new ArrayList<>();
    public Integer blueOne;
    public Integer blueTwo;

    public ComposeVariable(Integer blueOne, Integer blueTwo) {
        this.blueOne = blueOne;
        this.blueTwo = blueTwo;
    }
}
