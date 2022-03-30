package com.example.service;

import com.example.pojo.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> selectMenuAll();

    /**
     * 增加菜单
     */

    void addMenu(Menu menu);
}
