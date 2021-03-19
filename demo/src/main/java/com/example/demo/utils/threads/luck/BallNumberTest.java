package com.example.demo.utils.threads.luck;

import com.example.demo.dto.LuckBallSimpleDto;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Author: zc
 * @Date: 2020/12/24 18:10
 */
public class BallNumberTest {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        long startTime = Calendar.getInstance().getTimeInMillis();
        List<String> res = getNumStr();
        System.out.println("size:" + res.size());
        long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("耗时：" + ((endTime - startTime) / 1000));

    }

    //生成所有的可能
    private static List<String> getNumStr() {
        List<String> res = new ArrayList<>();
        Callable<List<String>> callable = null;
        CompletionService<List<String>> completionService = new ExecutorCompletionService<>(executorService);
        int count = 0;
        for (int blue = 1; blue < 12; blue++) {
            for (int blue2 = blue + 1; blue2 < 13; blue2++) {
                callable = new ComposeStrCallable(blue, blue2);
                completionService.submit(callable);
                count += 1;
            }
        }

        try {
            for (int i = 0; i < count; i++) {
                res.addAll(completionService.take().get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return res;
    }

    private static Set<LuckBallSimpleDto> getNumObj() {
        Set<LuckBallSimpleDto> res = new HashSet<>();
        Callable<Set<LuckBallSimpleDto>> callable = null;
        CompletionService<Set<LuckBallSimpleDto>> completionService = new ExecutorCompletionService<>(
                executorService);
        int count = 0;
        for (int blue = 1; blue < 12; blue++) {
            for (int blue2 = blue + 1; blue2 < 13; blue2++) {
                callable = new ComposeNumCallable(blue, blue2);
                completionService.submit(callable);
                count += 1;
            }
        }

        try {
            for (int i = 0; i < count; i++) {
                res.addAll(completionService.take().get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return res;
    }

}
