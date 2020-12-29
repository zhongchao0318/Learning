package com.example.demo.ball;

import com.example.demo.utils.threads.luck.ComposeObjCallable;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.concurrent.*;

/**
 * @Author: zc
 * @Date: 2020/12/28 14:27
 */
@SpringBootTest
public class CompostTests {
    private static final Logger logger = LoggerFactory.getLogger(CompostTests.class);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Test
    void getNumStr() {
        long startTime = Calendar.getInstance().getTimeInMillis();
        logger.info("开始时间：{}", startTime);
        int res = 0;
        Callable<Integer> callable = null;
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        int count = 0;
        for (int blue = 1; blue < 12; blue++) {
            for (int blue2 = blue + 1; blue2 < 13; blue2++) {
                callable = new ComposeObjCallable(blue, blue2);
                completionService.submit(callable);
                count += 1;
            }
        }

        try {
            for (int i = 0; i < count; i++) {
                res = res + (completionService.take().get());
                logger.info("res:{}",res);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        long endTime = Calendar.getInstance().getTimeInMillis();
        logger.info("总耗时：{}", (endTime - startTime) / 60000);
    }
}
