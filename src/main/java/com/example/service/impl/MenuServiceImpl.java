package com.example.service.impl;

import com.example.mapper.MenuMapper;
import com.example.pojo.Menu;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> selectMenuAll() {
        List<Menu> menus = menuMapper.selectMenuAll();
        return menus;
    }

    @Override
    public void addMenu(Menu menu) {
        menuMapper.addMenu(menu);
    }
}
