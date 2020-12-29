package com.example.demo.utils.threads.luck;

import com.example.demo.config.SpringBeanUtils;
import com.example.demo.entity.luck.Compose;
import com.example.demo.service.luck.impl.IComposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author: zc
 * @Date: 2020/12/28 11:37
 */
@Service
@Component
public class ComposeObjCallable implements Callable<Integer> {
    private Integer blueOne;
    private Integer blueTwo;
    private int count = 0;
    private IComposeService composeService;

    public ComposeObjCallable() {
        this.composeService = SpringBeanUtils.getBean(IComposeService.class);
    }

    public ComposeObjCallable(Integer blueOne, Integer blueTwo) {
        this.blueOne = blueOne;
        this.blueTwo = blueTwo;
        this.composeService = SpringBeanUtils.getBean(IComposeService.class);
    }

    @Override
    public Integer call() throws Exception {
        Compose compose = null;
        List<Compose> list = new ArrayList<>();
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
                            compose = new Compose();
                            compose.setCycle((red + red2 + red3 + red4 + red5 + blueOne + blueTwo) + "");
                            compose.setRedOne("" + red);
                            compose.setRedTwo("" + red2);
                            compose.setRedThree("" + red3);
                            compose.setRedFour("" + red4);
                            compose.setRedFive("" + red5);
                            compose.setBlueOne("" + blueOne);
                            compose.setBlueTwo("" + blueTwo);
                            list.add(compose);
                        }
                    }
                }
            }

        }
        //21425712
        count = list.size();
        //3500
        int page = count % 1000 > 0 ? (count / 1000) + 1 : count / 1000;
        for (int i = 1; i <= page; i++) {
            composeService.saveAll(list.subList((i - 1) * 1000, i == page ? count : i * 1000));
        }
        list.clear();
        return count;
    }
}
