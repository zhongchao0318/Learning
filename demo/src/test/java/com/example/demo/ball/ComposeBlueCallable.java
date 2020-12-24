package com.example.demo.ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

/**
 * @Author: zc
 * @Date: 2020/12/24 15:57
 */
public class ComposeBlueCallable implements Callable<Set<String>> {
    private Set<String> res = new TreeSet<>();
    private List<Integer> rOne = new ArrayList<>();
    private List<Integer> rTwo = new ArrayList<>();
    private List<Integer> rThree = new ArrayList<>();
    private List<Integer> rFour = new ArrayList<>();
    private List<Integer> rFive = new ArrayList<>();
    private Integer one2;
    private Integer two2;

    public ComposeBlueCallable(
             Integer one2
            , Integer two2
            , List<Integer> rOne
            , List<Integer> rTwo
            , List<Integer> rThree
            , List<Integer> rFour
            , List<Integer> rFive
    ) {
        this.one2 = one2;
        this.two2 = two2;
        this.rOne.addAll(rOne);
        this.rTwo.addAll(rTwo);
        this.rThree.addAll(rThree);
        this.rFour.addAll(rFour);
        this.rFive.addAll(rFive);
    }

    @Override
    public Set<String> call() throws Exception {
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        StringBuffer temp = null;
        flag_one:
        for (int j = 1; j < 32; j++) {
            if (j != 1) {
                rOne.remove(rOne.size() - 1);
            }
            rOne.add(j);
            one = getDataEx(rOne);
            flag_two:
            for (int k = j + 1; k < 33; k++) {
                if (k != 2) {
                    rTwo.remove(rTwo.size() - 1);
                }
                rTwo.add(k);
                two = getDataEx(rTwo);
                flag_three:
                for (int m = k + 1; m < 34; m++) {
                    if (m != 3) {
                        rThree.remove(rThree.size() - 1);
                    }
                    rThree.add(m);
                    three = getDataEx(rThree);
                    flag_four:
                    for (int n = m + 1; n < 35; n++) {
                        if (n != 4) {
                            rFour.remove(rFour.size() - 1);
                        }
                        rFour.add(n);
                        four = getDataEx(rFour);
                        flag_five:
                        for (int p = n + 1; p < 36; p++) {
                            if (p != 5) {
                                rFive.remove(rFive.size() - 1);
                            }
                            rFive.add(p);
                            five = getDataEx(rFive);
                            temp = new StringBuffer();
                            temp.append(one).append(" ")
                                    .append(two).append(" ")
                                    .append(three).append(" ")
                                    .append(four).append(" ")
                                    .append(five).append(" ")
                                    .append(one2).append(" ")
                                    .append(two2).append(" ");
                            res.add(temp.toString());
                        }
                    }
                }
            }

        }
        return res;
    }

    private int getDataEx(List<Integer> mSourceData) {
        int num, sum, i, j;
        List<Integer> list = new ArrayList<>();
        for (i = 0; i < mSourceData.size(); i++) {
            num = mSourceData.get(i);
            if (list.size() == 0) {
                list.add(num);
                list.add(1);
            } else {
                for (j = 0; j < list.size(); j += 2) {
                    if (list.get(j) == num) {
                        list.set(j + 1, list.get(j + 1) + 1);
                        break;
                    }
                }
                if (j >= list.size()) {
                    list.add(num);
                    list.add(1);
                }
            }
        }
        i = mSourceData.size();
        float sum2 = 0f;
        for (j = 0; j < list.size(); j += 2) {
            sum2 += list.get(j) * list.get(j + 1) / (float) i;
        }
        sum =  Math.round(sum2);
        return sum;
    }
}
