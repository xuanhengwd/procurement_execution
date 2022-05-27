package com.example.controller;


import com.example.pojo.Dept;
import com.example.pojo.Flow;
import com.example.service.ActivitiService;
import com.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/event")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ActivitiService activitiService;
    /**
     * 条件查询
     * @param flow
     * @param curPage
     * @param pageCount
     * @return
     */
    @RequestMapping(value = "/selectEventionByCondition", method = RequestMethod.POST)
    @ResponseBody
    List<Flow> selectDeptByCondition(Flow flow, int curPage, int pageCount) {

        List<Flow> flows = eventService.selectDeptByCondition(flow, curPage, pageCount);
        return flows;
    }


    /**
     * 流程部署
     * @param id
     * @return
     */
    @RequestMapping(value = "/deployFlow", method = RequestMethod.POST)
    @ResponseBody
    String deployFlow(int id) {

        //通过id 查询key+path
        Flow flow = eventService.selectFlowById(id);
        String name = flow.getFlowName();
        String key = flow.getFlowKey();
        String path = flow.getFilepath();
        Integer state = flow.getState();
        //已经部署了
        if(state.equals(1)){
            return "error";
        }
//        System.out.println(name+"--->"+key+"-->"+path);
        //部署流程
        String deployment = activitiService.deployment(name, key, path);
        if("success".equals(deployment)){
            //修改状态
            int state1 = 1;
            eventService.updateFlowById(id,state1);
            return "success";
        }else {
            return "error";
        }

    }


    /**
     * 禁用流程
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteFlow", method = RequestMethod.POST)
    @ResponseBody
    String deleteFlow(int id) {
        //根据id 获取key
        Flow flow = eventService.selectFlowById(id);
        String key = flow.getFlowKey();
        Integer state1 = flow.getState();

        if(state1.equals(0)){
            return "error";
        }
        //获取流程部署id
        String deployId = activitiService.getDeployIdByKey(key);
        //根据流程id删除部署
        String deleteResult = activitiService.deleteDeployment(deployId);
        if("success".equals(deleteResult)){
            //更新flow表
            int state = 0;
            eventService.updateFlowById(id,state);
            return "success";
        }

        return "error";



    }
}
