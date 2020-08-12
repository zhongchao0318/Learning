package com.example.demo.service.menu;

import com.example.demo.dao.MenuDao;
import com.example.demo.dto.MenuSimpleDto;
import com.example.demo.entity.MenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录业务逻辑
 */
@Service
public class IMenuService implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<MenuSimpleDto> getSimpleMenu(String site) {
        List<MenuSimpleDto> list = new ArrayList<>();
        List<MenuInfo> menuInfos = getMenuList(site, true);
        return null;
    }

    /**
     * 查询目录
     *
     * @param site   目录位置
     * @param isShow 是否显示
     * @return 目录集合
     */
    private List<MenuInfo> getMenuList(String site, boolean isShow) {
        return menuDao.findBySiteAndShow(site, isShow);
    }


}
