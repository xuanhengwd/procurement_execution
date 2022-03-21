package com.example.test;

import com.example.pojo.Role;
import com.example.service.impl.RoleServiceImpl;
import org.junit.Test;

import java.util.List;

public class RoleTest {

    @Test
    public void roleSelectAll(){
        RoleServiceImpl roleService=new RoleServiceImpl();
        List<Role> roles = roleService.selectRoleAll();

        System.out.println(roles);
    }
}
