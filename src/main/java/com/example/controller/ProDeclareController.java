package com.example.controller;

import com.example.pojo.ProDeclare;
import com.example.pojo.User;
import com.example.service.ActivitiService;
import com.example.service.ProDeclareService;
import com.example.service.UserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/proDeclare")
@CrossOrigin
public class ProDeclareController {


    @Autowired
    private ProDeclareService proDeclareService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;


    private final static String ProDeclareKey="biding";


    /**
     * 查询采购信息
     * @param proDeclare
     * @param curPage
     * @param pageCount
     * @return
     */
    @RequestMapping(value = "/selectProDeclareByCondition", method = RequestMethod.POST)
    @ResponseBody
    List<ProDeclare> selectProDeclareByCondition(ProDeclare proDeclare,int curPage,int pageCount){
        List<ProDeclare> proDeclares = proDeclareService.selectProDeclareByCondition(proDeclare, curPage, pageCount);
        return proDeclares;

    }


    /**
     * 新增采购执行
     * @return
     */
    @RequestMapping(value = "/addExecute", method = RequestMethod.POST)
    @ResponseBody
    String addExecute(int userId,int businessKey){

        //判断是不是申请人
        ProDeclare proDeclare = proDeclareService.selectProDeclareById(businessKey);
        //申请人编号
        String applicant_no = proDeclare.getApplicant_no();
        //根据编号获取id
        User user = userService.selectUserByNo(applicant_no);
        if(userId!=user.getId()){
            return "error";
        }
        //判断是不是未审核状态
        String state = proDeclare.getState();
        if(!"3".equals(state)){
            return "error";
        }

        //开启流程实例  传入流程key 业务的businessKey

        String businessKeyStr=""+businessKey;
        String instanceId = activitiService.startProcess(ProDeclareKey, businessKeyStr);//返回实例id
        //完成任务
        int flag = 1;
        activitiService.completeTask(instanceId,userId,flag);
        //修改状态为招标审核
        //1.根据id来修改状态
        proDeclareService.updateStateById(businessKey,"4");
        return "success";

    }


//    /**
//     * 完成招标申请任务
//     * @param userId
//     * @param proId
//     * @param flag
//     * @return
//     */
//    @RequestMapping(value = "/completeTask1", method = RequestMethod.POST)
//    @ResponseBody
//    String completeTask1(int userId,int proId,int flag){
//        //判断是不是最终执行人
//        ProDeclare proDeclare = proDeclareService.selectProDeclareById(proId);
//        String deptPrincipalNo = proDeclare.getDept_principal_no();
//        //根据编号获取id
//        User user = userService.selectUserByNo(deptPrincipalNo);
//
//        String assignee = ""+userId;
//        String businessKey = ""+proId;
//        if(flag == 0){
//            //修改状态为拒绝
//            proDeclareService.updateStateById(proId,"3");
//        }
//        Task task = taskService.createTaskQuery()
//                .taskAssignee(assignee)
//                .processInstanceBusinessKey(businessKey)
//                .singleResult();
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("result",flag);
//        if(task!=null){
//            taskService.complete(task.getId(),map);
//            if(userId==user.getId()){
//                //是最终执行人 需要修改状态
//                proDeclareService.updateStateById(proId,"5");
//            }
//            return "success";
//        }
//        return "error";
//    };
}
