package com.example.controller;

import com.example.pojo.Role;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    //查询所有
    @RequestMapping("/selectRoleAll")
    @ResponseBody
    public List<Role> selectRoleAll(){
        List<Role> roles = roleService.selectRoleAll();
        System.out.println(roles);
        return roles;
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteRoleById",method = RequestMethod.POST)
    @ResponseBody
    String deleteRoleById(int id){
        roleService.deleteRoleById(id);
        return "true";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteRoleByIds",method = RequestMethod.POST)
    @ResponseBody
    String deleteRoleByIds(int[] ids){
        roleService.deleteRoleByIds(ids);
        return "true";
    }

    /**
     * 添加角色信息
     * @param role
     * @param menuIds
     * @return
     */
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
    String addRole(Role role,int[] menuIds){
        roleService.addRole(role,menuIds);
        return "true";
    }

    /**
     * 修改角色信息
     * @param role
     * @param menuIds
     * @return
     */
    @RequestMapping(value = "/updateRoleById",method = RequestMethod.POST)
    @ResponseBody
    String updateRoleById(Role role,int[] menuIds){
        roleService.updateRoleById(role,menuIds);
        return "true";
    }

    /**
     * 条件查询  name
     * @param role
     * @return
     */
    @RequestMapping(value = "/selectRoleByCondition",method = RequestMethod.POST)
    @ResponseBody
    List<Role> selectRoleByCondition(Role role){
        List<Role> roles = roleService.selectRoleByCondition(role);
        return roles;
    }
    /**
     * 角色数量
     * @return
     */
    @RequestMapping(value = "/roleCount")
    @ResponseBody
    int roleCount(){
        return roleService.roleCount();
    }


}
