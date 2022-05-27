package com.example.controller;

import com.example.pojo.*;
import com.example.service.*;
import com.example.util.ProcessUtil;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private CheckProcessService checkProcessService;

    @Autowired
    private ProjectInfoService projectInfoService;

    private final static String ProDeclareKey = "biding";

    private final static String busiType = "1"; //招标


    /**
     * 查询采购信息
     *
     * @param proDeclare
     * @param curPage
     * @param pageCount
     * @return
     */
    @RequestMapping(value = "/selectProDeclareByCondition", method = RequestMethod.POST)
    @ResponseBody
    List<ProDeclare> selectProDeclareByCondition(@RequestParam("userId") int userId, ProDeclare proDeclare, int curPage, int pageCount) {
        //获取用户编号
        User user = userService.selectById(userId);
        String userNo = user.getUserNo();
        //System.out.println(userNo);
        proDeclare.setApplicant_no(userNo);
        List<ProDeclare> proDeclares = proDeclareService.selectProDeclareByCondition(proDeclare, curPage, pageCount);
        return proDeclares;

    }


    /**
     * 新增招标
     *
     * @return
     */
    @RequestMapping(value = "/addExecute", method = RequestMethod.POST)
    @ResponseBody
    String addExecute(int userId, int proId) {

        //判断是不是申请人
        ProDeclare proDeclare = proDeclareService.selectProDeclareById(proId);
        //申请人编号
        String applicant_no = proDeclare.getApplicant_no();
        //根据编号获取id
        User user = userService.selectUserByNo(applicant_no);
        if (userId != user.getId()) {
            return "error";
        }
        //判断是不是未审核状态
        String state = proDeclare.getState();
        if (!"3".equals(state)) {
            return "error";
        }

        //开启流程实例  传入流程key 业务的businessKey
        //获取businesskey

        String businessKey = proDeclare.getPro_no();
        String instanceId = activitiService.startProcess(ProDeclareKey, businessKey);//返回实例id
        //完成任务
        int flag = 1;
        activitiService.completeTask(instanceId, userId, flag);
        //修改状态为招标审核
        //1.根据id来修改状态
        proDeclareService.updateStateById(proId, "4");
        //创建进度表 传入 采购申报
        checkProcessService.createProcess(proDeclare, busiType);
        return "success";

    }


    /**
     * 获取招标的表
     * @param userId
     * @return
     */
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
                //获取业务主键==项目编号
                String proNo = task.getBusinessKey();
                //通过项目编号获取申报信息
                ProDeclare proDeclare = proDeclareService.selectProDeclareByNo(proNo);
                if("4".equals(proDeclare.getState())){
                    proDeclares.add(proDeclare);
                }
            }
            return proDeclares;
        }
        return null;
    }


    /**
     * 完成招标申请任务
     *
     * @param userId
     * @param proId
     * @param flag
     * @return
     */
    @RequestMapping(value = "/completeTask", method = RequestMethod.POST)
    @ResponseBody
    String completeTask(int userId, int proId, int flag) {
        //
        String assignee = "" + userId;
        //获取项目编号-》业务的主键
        //根据id获取当前业务
        ProDeclare proDeclare = proDeclareService.selectProDeclareById(proId);
        //项目编号 == 业务主键
        String businessKey = proDeclare.getPro_no();//项目编号
        //判断是不是最终执行人
        String deptPrincipalNo = proDeclare.getDept_principal_no();
        //根据编号获取id 部门负责人的对象信息
        User user = userService.selectUserByNo(deptPrincipalNo);

        Task task = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .processInstanceBusinessKey(businessKey)
                .singleResult();

        //设置流程变量
        Map<String, Object> map = new HashMap<>();
        map.put("result", flag);

        if (task != null) {
            taskService.complete(task.getId(), map);
            //进度表更新
            //获取当前的进度表
            CheckProcess checkProcess = checkProcessService.getProcessByProNoAndBusiType(businessKey, busiType);
            //拒绝
            if (flag == 0) {
                //修改状态为拒绝
                proDeclareService.updateStateById(proId, "0");
                //根据项目编号， 状态改为拒绝3
                checkProcess.setAudit_state("3");//任务拒绝
                // TODO: 2022/5/20 审核意见需要传过来
                checkProcess.setAudit_opinion("不通过");
            } else if (userId == user.getId()) {
                //进度表
                checkProcess.setAudit_state("2");//任务成功
                //获取当前状态
                String stateStr = proDeclare.getState();
                //形成项目信息表
                createProjectInfo(proDeclare);
                //是最终执行人 需要修改状态
                //进入下一个阶段
                int state = Integer.parseInt(stateStr) + 1;
                stateStr = "" + state;

                //改变状态
                proDeclareService.updateStateById(proId, stateStr);
            }
            //当下的process
            String process = checkProcess.getAudit_process();
            //进度+1
            int curProcess = Integer.parseInt(process)+1;
            process = ""+curProcess;
            checkProcess.setAudit_process(process);
            //当前的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String curDate = sdf.format(new Date());
            checkProcess.setAudit_date(curDate);
            //更新
            checkProcessService.updateCheckProcess(checkProcess);
            return "success";
        }
        return "error";
    }

    ;

    void createProjectInfo(ProDeclare proDeclare) {
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
        projectInfo.setContract_no(proDeclare.getPro_no());
        projectInfo.setContract_name("采购合同");
        projectInfoService.addProjectInfo(projectInfo);


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
