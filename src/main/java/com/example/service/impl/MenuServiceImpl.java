package com.example.service.impl;

import com.example.mapper.MenuMapper;
import com.example.mapper.RolePermissionMapper;
import com.example.pojo.Menu;
import com.example.service.MenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Menu> selectMenuAll(int curPage,int pageCount) {
        PageHelper.startPage(curPage, pageCount);
        List<Menu> menus = menuMapper.selectMenuAll();
        return menus;
    }

    @Override
    public void addMenu(Menu menu) {
        menuMapper.addMenu(menu);
    }

    @Override
    public void deleteMenuById(int id) {
        System.out.println("s:"+id);
        //级联删除  先删权限表 在删 菜单表
        //menuMapper.deleteRolePerByMenuId(id);
        rolePermissionMapper.deleteRolePerByMenuId(id);
        menuMapper.deleteMenuById(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteMenuByIds(int[] ids) {
        rolePermissionMapper.deleteRolePerByMenuIds(ids);
        menuMapper.deleteMenuByIds(ids);
    }

    @Override
    public void updateMenuById(Menu menu) {
        menuMapper.updateMenuById(menu);
    }

    @Override
    public List<Menu> selectMenuByCondition(Menu menu,int curPage,int pageCount) {
        String name = menu.getName();
        String state = menu.getState();
        if (name != null && name.length() > 0) {
            menu.setName("%"+name+"%");
        }
        if (state != null && state.length() > 0) {
            menu.setState("%"+state+"%");
        }
        PageHelper.startPage(curPage,pageCount);
        List<Menu> menus = menuMapper.selectMenuByCondition(menu);
        return menus;
    }

    @Override
    public int menuCount() {
        int count = menuMapper.menuCount();
        return count;
    }
}
