package com.example.demo.dao;

import com.example.demo.entity.MenuInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 目录dao
 */
public interface MenuDao extends JpaRepository<MenuInfo, String> {
    List<MenuInfo> findBySiteAndShow(String site, boolean isShow);
}
