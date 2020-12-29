package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class StringSpit {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            list.add(i);
        }
        int count = list.size();
        int page = count % 10 > 0 ? (count / 10) + 1 : count / 10;
        for (int i = 1; i <= page; i++) {
            for (Integer integer : list.subList((i - 1) * 10, i == page ? count  : i * 10)) {
                System.out.print(integer + "\t");
            }
            System.out.println();
        }
    }

    void demo1() {
        int count = 3500;
        int page = count % 1000 > 0 ? (count / 1000) + 1 : count / 1000;
        for (int i = 1; i <= page; i++) {
            log.info("{}\t{}", (i - 1) * 1000, i == page ? count - 1 : i * 1000);
        }
    }

    void rmsDemo() {
        String text = "111>修改>222,333>新增>add,444>删除>delete";
        String[] str = text.replaceAll(" ", "").split(",");
        for (int i = 0; i < str.length; i++) {
            String[] operate = str[i].split(">");
            if (operate.length != 3) {
                log.error(Arrays.toString(operate));
            } else {
                if (operate[1].equals("新增")) {
                    log.info("需要新增：" + operate[0]);
                } else if (operate[1].equals("修改")) {
                    log.info("将" + operate[0] + "修改" + operate[2]);
                } else if (operate[1].equals("删除")) {
                    log.info("将" + operate[0] + "删除");
                } else {
                    log.error(str[i]);
                }
            }

        }
    }
}
