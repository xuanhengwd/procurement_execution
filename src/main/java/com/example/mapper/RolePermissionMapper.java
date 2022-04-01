package com.example.mapper;

import com.example.pojo.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {

    /**
     * 根据menuid删除角色权限
     * @param id
     */
    void deleteRolePerByMenuId(int id);
    /**
     * 根据menuid数组 删除角色权限
     * @param ids
     */
    void deleteRolePerByMenuIds(@Param("ids") int[] ids);

    /**
     * 根据roleid 删除权限
     * @param id
     */
    void deleteRolePerByRoleId(int id);

    /**
     * 根据roleid  数组 批量删除
     * @param ids
     */
    void deleteRolePerByRoleIds(@Param("ids") int[] ids);

    /**
     * 添加权限
     * @param rolePermissions
     */
    void addRolePer(@Param("rolePers") List<RolePermission> rolePermissions);



}
