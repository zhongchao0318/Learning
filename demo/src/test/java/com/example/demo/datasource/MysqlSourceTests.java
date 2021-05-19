package com.example.demo.datasource;

import com.example.demo.dao.luck.CycleRecordDao;
import com.example.demo.entity.luck.CycleRecord;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

/**
 * @Author: zc
 * @Date: 2021/3/19 10:18
 */
@SpringBootTest
public class MysqlSourceTests {

    private static final Logger logger = LoggerFactory.getLogger(MysqlSourceTests.class);

    @Autowired
    private CycleRecordDao cycleRecordDao;

    private EntityManager entityManager;

    @Test
    void findTest() {
        CycleRecord cycleRecord = null;

        try {
            while (true) {
                logger.info(">>>>>>>>start>>>>>>");
                try {
                    cycleRecord = cycleRecordDao.findByStatus(55);
                } catch (Exception e) {

                }
                logger.info(cycleRecord.toString());
                logger.info(">>>>>>>>end>>>>>>");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
