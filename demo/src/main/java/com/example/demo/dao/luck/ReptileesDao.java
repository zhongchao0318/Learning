package com.example.demo.dao.luck;

import com.example.demo.entity.luck.Reptiles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 存储原始记录
 *
 * @Author: zc
 * @Date: 2020/12/15 13:53
 */
public interface ReptileesDao extends JpaRepository<Reptiles, String> {
}
