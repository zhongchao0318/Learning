package com.example.demo.ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

/**
 * @Author: zc
 * @Date: 2020/12/24 14:33
 */
public class ComposeCallable implements Callable<Set<String>> {
    private Set<String> res = new TreeSet<>();
    private Integer one;
    private List<Integer> rTwo = new ArrayList<>();
    private List<Integer> rThree = new ArrayList<>();
    private List<Integer> rFour = new ArrayList<>();
    private List<Integer> rFive = new ArrayList<>();
    private List<Integer> bOne = new ArrayList<>();
    private List<Integer> bTwo = new ArrayList<>();
    private Integer start;

    public ComposeCallable(
            Integer start
            , Integer one
            , List<Integer> rTwo
            , List<Integer> rThree
            , List<Integer> rFour
            , List<Integer> rFive
            , List<Integer> bOne
            , List<Integer> bTwo) {
        this.start = start;
        this.one = one;
        this.rTwo.addAll(rTwo);
        this.rThree.addAll(rThree) ;
        this.rFour.addAll(rFour);
        this.rFive.addAll(rFive) ;
        this.bOne.addAll(bOne) ;
        this.bTwo.addAll(bTwo) ;
    }

    @Override
    public Set<String> call() throws Exception {
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int one2 = 0;
        int two2 = 0;
        StringBuffer temp = null;
        flag_two:
        for (int k = start + 1; k < 33; k++) {
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
                        flag_b_one:
                        for (int q = 1; q < 12; q++) {
                            if (q != 1) {
                                bOne.remove(bOne.remove(bOne.size() - 1));
                            }
                            bOne.add(q);
                            one2 = getDataEx(bOne);
                            for (int r = q + 1; r < 13; r++) {
                                if (r != 2) {
                                    bTwo.remove(bTwo.size() - 1);
                                }
                                bTwo.add(r);
                                two2 = getDataEx(bTwo);
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
        sum = (int) sum2;
        return sum;
    }
}
