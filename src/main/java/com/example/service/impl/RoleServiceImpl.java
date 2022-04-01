package com.example.service.impl;

import com.example.mapper.RoleMapper;
import com.example.mapper.RolePermissionMapper;
import com.example.pojo.Role;
import com.example.pojo.RolePermission;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final RolePermissionMapper rolePermissionMapper;

    public RoleServiceImpl(RoleMapper roleMapper, RolePermissionMapper rolePermissionMapper) {
        this.roleMapper = roleMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public List<Role> selectRoleAll() {
        List<Role> roles = roleMapper.selectRoleAll();
        return roles;
    }

    @Override
    public void deleteRoleById(int id) {
        //级联删除
        rolePermissionMapper.deleteRolePerByRoleId(id);
        roleMapper.deleteRoleById(id);
    }

    @Override
    public void deleteRoleByIds(int[] ids) {
        rolePermissionMapper.deleteRolePerByRoleIds(ids);
        roleMapper.deleteRoleByIds(ids);
    }

    @Override
    public void addRole(Role role, int[] menuIds) {
        //角色信息插入
        roleMapper.addRole(role);
        int roleId=role.getId();
        //权限信息
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (int menuId : menuIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setMenuId(menuId);
            rolePermissions.add(rolePermission);
        }
        //插入权限
        rolePermissionMapper.addRolePer(rolePermissions);
    }

    @Override
    public void updateRoleById(Role role, int[] menuIds) {
        //修改角色表
        roleMapper.updateRoleById(role);
        //修改权限表
        //1.删除roleid的所以数据
        int roleId = role.getId();
        rolePermissionMapper.deleteRolePerByRoleId(roleId);
        //2.插入新的权限
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (int menuId : menuIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setMenuId(menuId);
            rolePermissions.add(rolePermission);
        }
        rolePermissionMapper.addRolePer(rolePermissions);

    }

    @Override
    public int roleCount() {
        int count = roleMapper.roleCount();
        return count;
    }

    @Override
    public List<Role> selectRoleByCondition(Role role) {

        String name = role.getName();
        if (name != null && name.length() > 0) {
            role.setName("%"+name+"%");
        }
        List<Role> roles = roleMapper.selectRoleByCondition(role);
        return roles;
    }
}
