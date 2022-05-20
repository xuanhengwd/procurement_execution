package com.example.controller;

import com.example.pojo.Contract;
import com.example.pojo.ProDeclare;
import com.example.pojo.ProjectInfo;
import com.example.pojo.User;
import com.example.service.ProDeclareService;
import com.example.service.ProjectInfoService;
import com.example.service.UserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProDeclareService proDeclareService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectInfoService projectInfoService;

    //获取当前要办理的任务  直接返回业务的表
    @RequestMapping(value = "/getTask", method = RequestMethod.POST)
    @ResponseBody
    List<ProDeclare> getTask(int userId){

        //需要：项目名称  业务名称：采购执行  合同签订。。。

        List<ProDeclare> proDeclares = new ArrayList<>();
        String assignee = ""+userId;
        //获取当前用户的所有任务

        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();

        //根据任务获取对应的业务
        if(tasks!=null){
            for (Task task : tasks) {
                //获取业务主键
                String businessKey = task.getBusinessKey();
                int businessId = Integer.parseInt(businessKey);
                //通过id 获取业务
                ProDeclare proDeclare = proDeclareService.selectProDeclareById(businessId);
                proDeclares.add(proDeclare);
            }
            return proDeclares;
        }
        return null;
    }



    // TODO: 2022/5/13 在办任务查询
    //获取正在办理的任务  直接返回业务的表 （进度信息有待考虑  考虑用进度表  用业务id作业一个键来查询）


    /**
     * 完成招标申请任务
     * @param userId
     * @param proId
     * @param flag
     * @return
     */
    @RequestMapping(value = "/completeTask", method = RequestMethod.POST)
    @ResponseBody
    String completeTask(int userId,int proId,int flag){
        String assignee = ""+userId;
        String businessKey = ""+proId;
        //判断是不是最终执行人
        // TODO: 2022/5/17 之后需要修改
        ProDeclare proDeclare = proDeclareService.selectProDeclareById(proId);
        String deptPrincipalNo = proDeclare.getDept_principal_no();
        //根据编号获取id
        User user = userService.selectUserByNo(deptPrincipalNo);

        Task task = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .processInstanceBusinessKey(businessKey)
                .singleResult();

        Map<String,Object> map = new HashMap<>();
        map.put("result",flag);
        if(task!=null){
            taskService.complete(task.getId(),map);
            //拒绝
            if(flag == 0){
                //修改状态为拒绝
                proDeclareService.updateStateById(proId,"0");
                //进度表修改成任务完成1 状态改为拒绝3
            }else if(userId==user.getId()){
                //获取当前状态
                String stateStr = proDeclare.getState();
                //判断形成  项目信息表   合同信息表
                if("4".equals(stateStr)){
                    //招标审核  =》》形成项目信息表
                    createProjectInfo(proDeclare);
                }else if("6".equals(stateStr)){
                    //中标审核==》》合同信息表 ===  明细表
                    // TODO: 2022/5/14 合同信息表生成
                    //获取项目信息表
                    ProjectInfo projectInfo = projectInfoService.selectProjectInfoByProNo(proDeclare.getPro_no());
                    creatContract(projectInfo);
                    System.out.println("----");
                }

                //是最终执行人 需要修改状态
                //验收之后 直接变为审核结束
                if("10".equals(stateStr)){
                    stateStr="2";
                }else {
                    //否则进入下一个阶段
                    int state = Integer.parseInt(stateStr)+1;
                    stateStr=""+state;
                }
                //改变状态
                proDeclareService.updateStateById(proId,stateStr);
            }
            return "success";
        }
        return "error";
    };

    void createProjectInfo(ProDeclare proDeclare){
        ProjectInfo projectInfo = new ProjectInfo();

        projectInfo.setPro_no(proDeclare.getPro_no());
        projectInfo.setPro_name(proDeclare.getPro_name());
        projectInfo.setFunds_no(proDeclare.getFunds_no());
        projectInfo.setFunds_name(proDeclare.getFunds_name());
        projectInfo.setFunds_type(proDeclare.getFunds_type());
        projectInfo.setDept_no(proDeclare.getDept_no());
        projectInfo.setDept_name(proDeclare.getDept_name());
        projectInfo.setApply_reason(proDeclare.getApply_reason());
        projectInfo.setBudget(proDeclare.getBudget());
        projectInfo.setDept_principal_no(proDeclare.getDept_principal_no());
        projectInfo.setDept_principal_name(proDeclare.getDept_principal_name());
        projectInfo.setPro_principal_name(proDeclare.getPro_principal_name());
        projectInfo.setPro_principal_no(proDeclare.getPro_principal_no());
        projectInfo.setPru_operno(proDeclare.getApplicant_no());
        projectInfo.setPru_opername(proDeclare.getApplicant_name());
        projectInfoService.addProjectInfo(projectInfo);



    }

    void creatContract(ProjectInfo projectInfo){

        Contract contract = new Contract();

        contract.setContract_no("");

    }

}
