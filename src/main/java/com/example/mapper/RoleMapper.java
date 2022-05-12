package com.example.mapper;

import com.example.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMapper {

    /**
     * 查询所有
     * @return
     */
    List<Role> selectRoleAll();

    /**
     * 根据id删除角色
     * @param id
     */
    void deleteRoleById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteRoleByIds(@Param("ids") int[] ids);

    /**
     * 添加角色
     * @param role
     */
    int addRole(Role role);

    /**
     * 根据id修改角色信息
     * @param role
     */
    void updateRoleById(Role role);

    /**
     * 角色数量
     * @return
     */
    int roleCount();

    /**
     * 按条件查询
     * @param role
     * @return
     */
    List<Role> selectRoleByCondition(Role role);

}
