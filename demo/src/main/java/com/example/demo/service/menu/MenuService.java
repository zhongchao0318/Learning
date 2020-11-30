package com.example.demo.service.menu;

import com.example.demo.dto.MenuSimpleDto;

import java.util.List;

/**
 * 目录
 */
public interface MenuService {

    /**
     * 查询site目录
     *
     * @param site
     * @return
     */
    List<MenuSimpleDto> getSimpleMenu(String site);

}
