package com.example.controller;

import com.example.pojo.Role;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询所有
    @RequestMapping("/selectRoleAll")
    @ResponseBody
    public List<Role> selectRoleAll(){
        List<Role> roles = roleService.selectRoleAll();
        System.out.println(roles);
        return roles;
    }

}
