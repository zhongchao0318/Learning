package com.example.demo.utils.threads.luck;

import com.example.demo.dto.LuckBallSimpleDto;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 生成
 *
 * @Author: zc
 * @Date: 2020/12/24 17:54
 */
public class ComposeNumCallable implements Callable<Set<LuckBallSimpleDto>> {
    private Set<LuckBallSimpleDto> res = new HashSet<>();
    private Integer blueOne;
    private Integer blueTwo;

    public ComposeNumCallable(Integer one2, Integer two2) {
        this.blueOne = one2;
        this.blueTwo = two2;
    }

    @Override
    public Set<LuckBallSimpleDto> call() throws Exception {
        LuckBallSimpleDto simpleDto = null;
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
                            simpleDto = new LuckBallSimpleDto();
                            simpleDto.setCycle((red + red2 + red3 + red4 + red5 + blueOne + blueTwo) + "");
                            simpleDto.setRedOne("" + red);
                            simpleDto.setRedTwo("" + red2);
                            simpleDto.setRedThree("" + red3);
                            simpleDto.setRedFour("" + red4);
                            simpleDto.setRedFive("" + red5);
                            simpleDto.setBlueOne("" + blueOne);
                            simpleDto.setBlueTwo("" + blueTwo);
                            res.add(simpleDto);
                        }
                    }
                }
            }

        }
        return res;
    }
}
