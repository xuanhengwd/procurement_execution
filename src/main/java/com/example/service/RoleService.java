package com.example.service;

import com.example.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> selectRoleAll(int curPage,int pageCount);

    /**
     * 根据id删除角色信息
     * @param id
     */
    void deleteRoleById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteRoleByIds(int[] ids);


    /**
     * 添加角色
     * @param role
     * @param menuIds
     */
    void addRole(Role role,int[] menuIds);

    /**
     * 修改角色信息
     * @param role
     * @param menuIds
     */
    void updateRoleById(Role role,int[] menuIds);

    /**
     * 角色数量
     * @return
     */
    int roleCount();

    /**
     * 条件查询
     * @param role
     * @return
     */
    List<Role> selectRoleByCondition(Role role,int curPage,int pageCount);
}
