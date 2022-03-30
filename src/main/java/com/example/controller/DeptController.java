package com.example.controller;


import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("deptController")
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/selectDeptAll")
    @ResponseBody
    List<Dept> selectDeptAll(){
        List<Dept> depts = deptService.selectDeptAll();
        System.out.println(depts);
        return depts;
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @RequestMapping(value = "/addDept",method = RequestMethod.POST)
    @ResponseBody
    String addDept(Dept dept){
        System.out.println(dept);
        deptService.addDept(dept);
        return "true";
    }

    /**
     * 根据id删除部门信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteDeptById",method = RequestMethod.POST)
    @ResponseBody
    String deleteDeptById(int id){
        deptService.deleteDeptById(id);
        return "true";
    }

    /**
     * 批量查询
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteDeptByIds",method = RequestMethod.POST)
    @ResponseBody
    String deleteDeptByIds(int[] ids){
        deptService.deleteDeptByIds(ids);
        return "true";
    }


    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @RequestMapping(value = "/updateDeptById",method = RequestMethod.POST)
    @ResponseBody
    String updateDeptById(Dept dept){
        deptService.updateDeptById(dept);
        return "true";
    }

    /**
     * 条件查询
     * @param dept
     * @return
     */
    @RequestMapping(value = "/selectDeptByCondition",method = RequestMethod.POST)
    @ResponseBody
    List<Dept> selectDeptByCondition(Dept dept){
        List<Dept> depts = deptService.selectDeptByCondition(dept);
        return depts;
    }

    /**
     * 查询所有部门的数量
     * @return
     */
    @RequestMapping(value = "/deptCount",method = RequestMethod.POST)
    @ResponseBody
    int deptCount(){
        return deptService.deptCount();
    }



}
