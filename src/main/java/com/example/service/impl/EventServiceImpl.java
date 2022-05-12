package com.example.service.impl;

import com.example.mapper.EventMapper;
import com.example.pojo.Dept;
import com.example.pojo.Flow;
import com.example.service.EventService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;


    @Override
    public List<Flow> selectDeptByCondition(Flow flow, int curPage, int pageCount) {
        String flowName = flow.getFlowName();
        if (flowName != null && flowName.length() > 0) {
            flow.setFlowName("%"+flowName+"%");
        }

        PageHelper.startPage(curPage,pageCount);
        List<Flow> flows = eventMapper.selectFlowByCondition(flow);
        return flows;
    }

    @Override
    public Flow selectFlowById(int id) {
        Flow flow = eventMapper.selectFlowById(id);
        return flow;
    }

    @Override
    public void updateFlowById(int id, int state) {
        eventMapper.updateFlowById(id,state);
    }
}
