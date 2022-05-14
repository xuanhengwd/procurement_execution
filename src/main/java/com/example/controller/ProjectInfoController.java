package com.example.controller;

import com.example.pojo.ProDeclare;
import com.example.pojo.ProjectInfo;
import com.example.pojo.User;
import com.example.service.ActivitiService;
import com.example.service.ProDeclareService;
import com.example.service.ProjectInfoService;
import com.example.service.UserService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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


    private final static String Key="winBiding";
    /**
     * 查询采购信息
     * @param projectInfo
     * @param curPage
     * @param pageCount
     * @return
     */
    @RequestMapping(value = "/selectProjectInfoByCondition", method = RequestMethod.POST)
    @ResponseBody
    List<ProjectInfo> selectProjectInfoByCondition(ProjectInfo projectInfo, int curPage, int pageCount){
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
     * 新增采购执行
     * @return
     */
    @RequestMapping(value = "/addApply", method = RequestMethod.POST)
    @ResponseBody
    String addExecute(int userId,int projectInfoId){
        //判断是不是申请人
        //获取项目编号
        ProjectInfo projectInfo = projectInfoService.selectProjectInfoById(projectInfoId);
        String proNo = projectInfo.getPro_no();
        ProDeclare proDeclare = proDeclareService.selectProDeclareByNo(proNo);
        //申请人编号
        String applicant_no = proDeclare.getApplicant_no();
        //根据编号获取id
        User user = userService.selectUserByNo(applicant_no);
        if(userId!=user.getId()){
            return "error";
        }
        //判断是不是中标审核状态
        String state = proDeclare.getState();
        if(!"5".equals(state)){
            return "error";
        }

        String instanceId = activitiService.startProcess(Key, ""+proDeclare.getId());//返回实例id
        //完成任务
        int flag = 1;
        activitiService.completeTask(instanceId,userId,flag);
        //修改状态为招标审核
        //1.根据id来修改状态
        proDeclareService.updateStateById(proDeclare.getId(),"6");
        return "success";

    }




}
