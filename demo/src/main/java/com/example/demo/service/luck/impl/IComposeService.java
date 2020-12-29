package com.example.demo.service.luck.impl;

import com.example.demo.dao.luck.ComposeDao;
import com.example.demo.entity.luck.Compose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author: zc
 * @Date: 2020/12/28 11:30
 */
@Service
public class IComposeService {
    @PersistenceContext
    private EntityManager entityManager;

    //    @Transactional(rollbackFor = Exception.class)
    @Transactional
    public void saveAll(List<Compose> list) {
        for (Compose compose : list) {
            entityManager.persist(compose);//insert插入操作
        }
        entityManager.flush();
        entityManager.clear();
    }
}
