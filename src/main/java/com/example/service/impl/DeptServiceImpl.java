package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> selectDeptAll() {
        List<Dept> depts = deptMapper.selectDeptAll();
        return depts;
    }

    @Override
    public void addDept(Dept dept) {
        deptMapper.addDept(dept);
    }

    @Override
    public void deleteDeptById(int id) {
        deptMapper.deleteDeptById(id);
    }

    @Override
    public void updateDeptById(Dept dept) {
        deptMapper.updateDeptById(dept);
    }


}
