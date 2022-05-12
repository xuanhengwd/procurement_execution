package com.example.mapper;

import com.example.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MenuMapper {
    List<Menu> selectMenuAll();

    /**
     * 添加菜单
     * @param menu
     */
    void addMenu(Menu menu);


//    /**
//     * 根据memuid删除权限
//     * @param id
//     */
//    void deleteRolePerByMenuId(int id);
    /**
     * 根据id删除menu
     * @param id
     */
    void deleteMenuById(int id);


//    /**
//     * 根据id数组 删除角色权限
//     * @param ids
//     */
//    void deleteRolePerByMenuIds(@Param("ids") int[] ids);
//    /**
//     * 批量删除menu
//     */
    void deleteMenuByIds(@Param("ids") int[] ids);



    /**
     * 根据id  修改菜单信息
     * @param menu
     */
    void updateMenuById(Menu menu);


    /**
     * 根据条件查询
     * @param menu
     * @return
     */
    List<Menu> selectMenuByCondition(Menu menu);

    /**
     * 菜单的个数
     * @return
     */
    int menuCount();





}
