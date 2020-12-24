package com.example.demo.utils.tree;


import com.example.demo.utils.threads.NumStrCallable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Huanyuan<T> {

    public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }

    private Lock lock = new ReentrantLock();
    private static Map<Integer, List<Integer>> phaseInfo = new TreeMap<>();    /*记录批次和号码*/
    private static Integer phaseSum = 0;
    private static int rate = 3;

    public static synchronized String getNumber(Integer length) {
        if (phaseInfo.keySet().size() > 3) {
            stop:
            for (Integer remove : phaseInfo.keySet()) {
                phaseInfo.remove(remove);
                break stop;
            }
        }
        int period = getPhaseSum();
        List<Integer> wn = new ArrayList<>();                       /*Winning numbers*/
        Random random = new Random();
        StringBuffer numStr = new StringBuffer();
        Integer tempNum = 0;
        boolean flag = false;
        for (int i = 0; i < length; i++) {
            do {
                tempNum = random.nextInt(7);
                flag = checkNumConut(tempNum, period, wn);
            } while (!flag);
            if (i == length - 1) {
                numStr.append(tempNum);
            } else {
                numStr.append(tempNum).append(", ");
            }
            wn.add(tempNum);
        }
        phaseInfo.put(period, wn);
        return numStr.toString();
    }

    private static boolean checkNumConut(Integer num, Integer phaseSum, List<Integer> wnTemp) {
        if (wnTemp.contains(num)) return false;                         /*重复出现*/
        if (phaseSum <= 3) return true;
        List<Integer> wn = null;                                        /*Winning numbers*/
        int count = 0;
        for (int i = 1; i < rate + 1; i++) {
            wn = phaseInfo.get(phaseSum - i);
            count = wn.contains(num) ? count + 1 : count;
        }
        return count < 3 ? true : false;
    }

    private static Integer getPhaseSum() {
        return phaseSum += 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable = new NumStrCallable(4);
//        for (int i = 0; i < 100; i++) {
//            FutureTask task = new FutureTask(callable);
//            new Thread(task).start();
//            System.out.println(task.get());
//        }
        String[] str = {"w", "s", "u", "q"};
        String res;
        Random random = new Random();
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < 100; i++) {
            int num2 = random.nextInt(4);
            res = str[num2];
            if (map.containsKey(res)) {
                map.put(res,map.get(res)+1);
            } else {
                map.put(res,1);
            }
        }
        for(String c : map.keySet()){
            System.out.println(c+"\t:"+map.get(c));
        }

    }

    public List<T> reduction(List<Tree<T>> nodes) {
        List<T> result = new ArrayList<>();
        for (Tree<T> tree : nodes) {
            result.add(tree.getT());
        }
        return null;
    }
}
