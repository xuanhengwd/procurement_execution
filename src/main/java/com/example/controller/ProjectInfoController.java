package com.example.controller;

import com.example.pojo.*;
import com.example.service.*;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 关于中标信息审核相关的controller层
 */
@Controller
@RequestMapping("/projectInfo")
@CrossOrigin
public class ProjectInfoController {

    @Autowired
    private ProjectInfoService projectInfoService;

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
    private ContractService contractService;


    private final static String Key="winBiding";
    private final static String busiType = "2"; //中标
    /**
     * 查询中标
     * @param projectInfo
     * @param curPage
     * @param pageCount
     * @return
     */
    @RequestMapping(value = "/selectProjectInfoByCondition", method = RequestMethod.POST)
    @ResponseBody
    List<ProjectInfo> selectProjectInfoByCondition(ProjectInfo projectInfo, int curPage, int pageCount,int userId){
        User user = userService.selectById(userId);
        String userNo = user.getUserNo();
        //System.out.println(userNo);
        projectInfo.setPru_operno(userNo);
        List<ProjectInfo> projectInfos = projectInfoService.selectProjectInfoByCondition(projectInfo, curPage, pageCount);
        return projectInfos;

    }

    @RequestMapping(value = "/updateProjectInfo", method = RequestMethod.POST)
    @ResponseBody
    String updateProjectInfo(ProjectInfo projectInfo){

        projectInfoService.updateProjectInfo(projectInfo);
        return "success";

    }
    /**
     * 新增中标信息申请
     * @return
     */
    @RequestMapping(value = "/addApply", method = RequestMethod.POST)
    @ResponseBody
    String addExecute(int userId,int projectInfoId){

        ProjectInfo projectInfo = projectInfoService.selectProjectInfoById(projectInfoId);
        //有没有填写
        if(projectInfo.getBid_price()==null||projectInfo.getBid_linkman()==null||projectInfo.getBid_date()==null
        ||projectInfo.getBid_winner()==null||projectInfo.getBid_tel()==null||projectInfo.getBid_reason()==null){
            return "empty";
        }

        //判断是不是申请人
        //获取项目编号
        String proNo = projectInfo.getPro_no();
        //通过项目编号来获取当下的申报表
        ProDeclare proDeclare = proDeclareService.selectProDeclareByNo(proNo);
        //申请人编号
        String applicant_no = proDeclare.getApplicant_no();
        //根据编号获取id
        User user = userService.selectUserByNo(applicant_no);
        if(userId!=user.getId()){
            return "error";
        }
        //判断是不是中标未审核状态
        String state = proDeclare.getState();
        if(!"5".equals(state)){
            return "error";
        }



        String instanceId = activitiService.startProcess(Key, proNo);//返回实例id
        //完成任务
        int flag = 1;
        activitiService.completeTask(instanceId,userId,flag);
        //修改状态为招标审核
        //1.根据id来修改状态
        proDeclareService.updateStateById(proDeclare.getId(),"6");
        //创建进度表
        checkProcessService.createProcess(proDeclare,busiType);

        return "success";

    }

    /**
     * 获取中标的表
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getTask", method = RequestMethod.POST)
    @ResponseBody
    List<ProjectInfo> getTask(int userId){
        //需要：项目名称  业务名称：采购执行  合同签订。。。

        List<ProjectInfo> projectInfos = new ArrayList<>();
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

                if("6".equals(proDeclare.getState())){
                    ProjectInfo projectInfo = projectInfoService.selectProjectInfoByProNo(proNo);
                    projectInfos.add(projectInfo);
                }

            }
            return projectInfos;
        }
        return null;
    }


    /**
     * 完成招标申请任务
     *
     * @param userId
     * @param projectInfoId
     * @param flag
     * @return
     */
    @RequestMapping(value = "/completeTask", method = RequestMethod.POST)
    @ResponseBody
    String completeTask(int userId,int projectInfoId,int flag){
        String assignee = ""+userId;
        ProjectInfo projectInfo = projectInfoService.selectProjectInfoById(projectInfoId);

        String proNo = projectInfo.getPro_no();
        //通过项目编号来获取当下的申报表
        ProDeclare proDeclare = proDeclareService.selectProDeclareByNo(proNo);
        Integer proId = proDeclare.getId();
        //项目编号 == 业务主键
        String businessKey = proDeclare.getPro_no();//项目编号
        //判断是不是最终执行人
        String deptPrincipalNo = proDeclare.getDept_principal_no();
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

                //是最终执行人 需要修改状态
                //进入下一个阶段
                int state = Integer.parseInt(stateStr) + 1;
                stateStr = "" + state;
                //形成合同信息表
                creatContract(projectInfo);
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





    void creatContract(ProjectInfo projectInfo){
        Contract contract = new Contract();
        contract.setContract_no(projectInfo.getContract_no());
        contract.setContract_name(projectInfo.getContract_name());
        contract.setApplicant_no(projectInfo.getPru_operno());
        contract.setApplicant(projectInfo.getPru_opername());
        contract.setDept_no(projectInfo.getDept_no());
        contract.setDept_name(projectInfo.getDept_name());
        contract.setPro_no(projectInfo.getPro_no());
        contract.setPro_name(projectInfo.getPro_name());
        contractService.addContract(contract);



    }




}
