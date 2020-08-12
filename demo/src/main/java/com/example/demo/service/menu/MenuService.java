package com.example.demo.service.menu;

import com.example.demo.dto.MenuSimpleDto;

import java.util.List;

/**
 * 目录
 */
public interface MenuService {

    List<MenuSimpleDto> getSimpleMenu(String site);
}
