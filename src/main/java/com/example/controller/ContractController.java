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
 * 合同相关
 */
@Controller
@RequestMapping("/contract")
@CrossOrigin
public class ContractController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ProDeclareService proDeclareService;

    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private CheckProcessService checkProcessService;

    @Autowired
    private TaskService taskService;

    private final static String Key = "contractDetails";

    private final static String busiType = "3"; //合同信息
    private final static String Curstate = "7";//状态

    /**
     * 合同查询
     *
     * @param contract
     * @param curPage
     * @param pageCount
     * @return
     */
    @RequestMapping(value = "/selectContractByCondition", method = RequestMethod.POST)
    @ResponseBody
    List<Contract> selectContractByCondition(Contract contract, int curPage, int pageCount, int userId) {
        User user = userService.selectById(userId);
        String userNo = user.getUserNo();
        //System.out.println(userNo);
        contract.setApplicant_no(userNo);
        List<Contract> contracts = contractService.selectContractByCondition(contract, curPage, pageCount);
        return contracts;

    }

    /**
     * 更新合同信息
     *
     * @param contract
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ResponseBody
    String updateById(Contract contract) {

        try {
            contractService.updateById(contract);
        } catch (Exception e) {
            return "error";
        }
        return "success";

    }

    /**
     * 新增合同明细申请
     *
     * @return
     */
    @RequestMapping(value = "/addApply", method = RequestMethod.POST)
    @ResponseBody
    String addExecute(int userId, int contractId) {

        Contract contract = contractService.selectContractById(contractId);
        //有没有填写
        if (contract.getPackageName() == null || contract.getBaoxqx() == null || contract.getAmount() == null
                || contract.getJiaohrq() == null || contract.getJiaf() == null || contract.getYif() == null
                || contract.getBingf() == null || contract.getTel_jiaf() == null || contract.getTel_yif() == null
                || contract.getTel_bingf() == null) {

            return "empty";
        }

        //判断是不是申请人
        //获取项目编号
        String proNo = contract.getPro_no();
        //通过项目编号来获取当下的申报表
        ProDeclare proDeclare = proDeclareService.selectProDeclareByNo(proNo);
        //申请人编号
        String applicant_no = proDeclare.getApplicant_no();
        //根据编号获取id
        User user = userService.selectUserByNo(applicant_no);
        if (userId != user.getId()) {
            return "error";
        }
        //判断是不是中标未审核状态
        String state = proDeclare.getState();
        if (!Curstate.equals(state)) {
            return "error";
        }


        String instanceId = activitiService.startProcess(Key, proNo);//返回实例id
        //完成任务
        int flag = 1;
        activitiService.completeTask(instanceId, userId, flag);
        //修改状态为招标审核
        //1.根据id来修改状态
        proDeclareService.updateStateById(proDeclare.getId(), "8");
        //创建进度表
        checkProcessService.createProcess(proDeclare, busiType);

        return "success";

    }

    /**
     * 获取合同信息表
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getTask", method = RequestMethod.POST)
    @ResponseBody
    List<Contract> getTask(int userId){
        //需要：项目名称  业务名称：采购执行  合同签订。。。

        List<Contract> contracts = new ArrayList<>();
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

                if("8".equals(proDeclare.getState())){
                    Contract contract = contractService.selectContractByProNo(proNo);
                    contracts.add(contract);
                }

            }
            return contracts;
        }
        return null;
    }


    /**
     * 完成合同任务
     *
     * @param userId
     * @param contractId
     * @param flag
     * @return
     */
    @RequestMapping(value = "/completeTask", method = RequestMethod.POST)
    @ResponseBody
    String completeTask(int userId,int contractId,int flag){
        String assignee = ""+userId;
        Contract contract = contractService.selectContractById(contractId);


        String proNo = contract.getPro_no();
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


}
