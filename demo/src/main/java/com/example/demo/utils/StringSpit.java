package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class StringSpit {
    public static void main(String[] args) {
        String text = "111>修改>222,333>新增>add,444>删除>delete";
        String[] str = text.replaceAll(" ","").split(",");
        for(int i = 0; i < str.length; i++){
            String[] operate = str[i].split(">");
            if(operate.length!= 3) {
                log.error(Arrays.toString(operate));
            }else{
                if(operate[1].equals("新增")) {
                    log.info("需要新增："+operate[0] );
                }else if (operate[1].equals("修改")) {
                    log.info("将"+operate[0] + "修改"+operate[2]);
                }else if(operate[1].equals("删除")){
                    log.info("将"+operate[0]+"删除");
                }else{
                    log.error(str[i]);
                }
            }

        }
     }
}
