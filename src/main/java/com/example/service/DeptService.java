package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询所有
    List<Dept> selectDeptAll();

    //添加部门
    void addDept(Dept dept);

    //删除
    void deleteDeptById(int id);

    //修改
    void updateDeptById(Dept dept);
}
