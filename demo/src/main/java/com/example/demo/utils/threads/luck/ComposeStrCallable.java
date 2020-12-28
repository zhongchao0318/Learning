package com.example.demo.utils.threads.luck;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author: zc
 * @Date: 2020/12/25 9:57
 */
public class ComposeStrCallable extends ComposeVariable<String> implements Callable<List<String>> {


    public ComposeStrCallable(Integer blueOne, Integer blueTwo) {
        super(blueOne, blueTwo);
    }

    @Override
    public List<String> call() throws Exception {
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
