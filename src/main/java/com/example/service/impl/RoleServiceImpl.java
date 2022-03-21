package com.example.service.impl;

import com.example.mapper.RoleMapper;
import com.example.pojo.Role;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectRoleAll() {
        List<Role> roles = roleMapper.selectRoleAll();
        return roles;
    }
}
