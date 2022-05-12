package com.example.service;

import com.example.pojo.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> selectMenuAll(int curPage,int pageCount);

    /**
     * 增加菜单
     */
    void addMenu(Menu menu);

    /**
     * 根据id删除菜单
     * @param id
     */
    void deleteMenuById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMenuByIds(int[] ids);

    /**
     * 根据id修改菜单信息
     * @param menu
     */
    void updateMenuById(Menu menu);

    /**
     * 根据条件查询 名称 和 状态
     * @param menu
     */
    List<Menu> selectMenuByCondition(Menu menu,int curPage,int pageCount);

    /**
     * 菜单个数
     * @return
     */
    int menuCount();
}
