package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {

    /**
     * 查询所有部门
     * @return
     */
    List<Dept> selectDeptAll();

    /**
     * 添加部门
     * @param dept
     */
    void addDept(Dept dept);


    /**
     * 根据id删除部门
     * @param id
     */
    void deleteDeptById(int id);

    /**
     * 批量删除
     * @param ints
     */
    void deleteDeptByIds(@Param("ids") int[] ints);

    /**
     * 根据id修改部门信息
     * @param dept
     */
    void updateDeptById(Dept dept);


    /**
     * 条件查询
     * @param dept
     * @return
     */
    List<Dept>selectDeptByCondition(Dept dept);

    int deptCount();






}
