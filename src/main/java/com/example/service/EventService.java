package com.example.service;

import com.example.pojo.Flow;

import java.util.List;

public interface EventService {

    //按条件查询
    List<Flow> selectDeptByCondition(Flow flow, int curPage, int pageCount);

    //根据id查询
    Flow selectFlowById(int id);

    //修改状态
    void updateFlowById(int id,int state);
}
