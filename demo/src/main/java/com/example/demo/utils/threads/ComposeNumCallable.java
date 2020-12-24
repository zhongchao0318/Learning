package com.example.demo.utils.threads;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @Author: zc
 * @Date: 2020/12/24 17:54
 */
public class ComposeNumCallable implements Callable<Set<String>> {
    private Set<String> res = new HashSet<>();
    private Integer blueOne;
    private Integer blueTwo;

    public ComposeNumCallable(Integer one2, Integer two2) {
        this.blueOne = one2;
        this.blueTwo = two2;
    }

    @Override
    public Set<String> call() throws Exception {
        StringBuffer temp = null;
        flag_one:
        for (int red = 1; red < 32; red++) {
            flag_two:
            for (int red2 = red + 1; red2 < 33; red2++) {
                flag_three:
                for (int red3 = red2 + 1; red3 < 34; red3++) {
                    flag_four:
                    for (int red4 = red3 + 1; red4 < 35; red4++) {
                        flag_five:
                        for (int red5 = red4 + 1; red5 < 36; red5++) {
                            temp = new StringBuffer();
                            temp.append(red).append(" ")
                                    .append(red2).append(" ")
                                    .append(red3).append(" ")
                                    .append(red4).append(" ")
                                    .append(red5).append(" ")
                                    .append(blueOne).append(" ")
                                    .append(blueTwo);
                            res.add(temp.toString());
                        }
                    }
                }
            }

        }
        return res;
    }
}
