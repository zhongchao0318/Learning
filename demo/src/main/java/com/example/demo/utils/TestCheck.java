package com.example.demo.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCheck {
    private static int num = 0;
    private static int max = 10;
    private static String[] move = new String[]{"A", "B", "C", "D", "E"};

    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    private static final String REGEX_PHONE = "^((|13|14|15|17|18|19)[0-9]{9})";

    private static final String REGEX_IDCARD = "\\d{17}[\\d|x]|\\d{15}";

    public static void main(String[] args) {
        String[] email = new String[]{"18520521318", "12131313113", "16161616161", "13913913913", "123",null,"430822199503182999"};
        for (String str : email) {
            System.out.println(checkContent(str, REGEX_IDCARD) ? "正确：\t"+str :"错误: \t"+str);
        }
    }

    public static boolean checkContent(String content, String regex) {
        if (content == null || regex == null || "".equals(content) || "".equals(regex)) return false;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        return m.matches();
    }

    /**
     * @param place  原位置   5 1
     * @param target 目标位置 1 5
     * @return
     */
    public static String[] moveStr(int place, int target) {
        place -= 1;
        target -= 1;
        String value = move[place];
        while (place > target) {
            move[place] = move[place - 1];
            place--;
        }
        //左移
        while (target > place) {
            move[place] = move[place + 1];
            place++;
        }
        move[target] = value;
        return move;
    }

    /**
     * 递归累加
     *
     * @param num
     * @return
     */
    private static int add(int num) {
        num += 1;
        return num == max ? num : add(num);
    }
}
