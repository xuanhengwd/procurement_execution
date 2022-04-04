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
    public void deleteDeptByIds(int[] ids) {

        deptMapper.deleteDeptByIds(ids);
    }

    @Override
    public void updateDeptById(Dept dept) {
        deptMapper.updateDeptById(dept);
    }

    @Override
    public List<Dept> selectDeptByCondition(Dept dept) {

        String deptName = dept.getDeptName();
        String deptNo = dept.getDeptNo();
        if (deptName != null && deptName.length() > 0) {
            dept.setDeptName("%"+deptName+"%");
        }
        if (deptNo != null && deptNo.length() > 0) {
            dept.setDeptNo("%"+deptNo+"%");
        }

        List<Dept> depts = deptMapper.selectDeptByCondition(dept);
        return depts;
    }

    @Override
    public int deptCount() {
        int count = deptMapper.deptCount();
        return count;
    }

    @Override
    public Boolean addDepts(List<Dept> depts) {
        boolean f = false;
        System.out.println(depts);
        deptMapper.addDepts(depts);
        f=true;
        return f;

    }


}
