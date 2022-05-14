package com.example.service.impl;


import com.example.service.ActivitiService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivitiServiceImpl implements ActivitiService {
    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    /**
     * 部署
     * @param name
     * @param key
     * @param path
     * @return
     */
    public String deployment(String name,String key,String path){
        //部署
        Deployment deploy = null;
        try {
            deploy = repositoryService.createDeployment()
                    .name(name)
                    .addClasspathResource(path)
                    .deploy();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


        //输出部署信息
        System.out.println("流程部署id：" + deploy.getId());
        System.out.println("流程部署名字：" + deploy.getName());
        System.out.println("流程定义key"+deploy.getKey());
        return "success";
    }


    /**
     * 获取部署id
     * @param key
     * @return
     */
    @Override
    public String getDeployIdByKey(String key) {


        Deployment deployment = null;
        try {
            deployment = repositoryService.createDeploymentQuery()
                    .processDefinitionKey(key)
                    .singleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return deployment.getId();
    }


    /**
     * 删除部署
     * @param deployId
     * @return
     */
    @Override
    public String deleteDeployment(String deployId) {

        try {
            repositoryService.deleteDeployment(deployId,true);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


    /**
     * 启动流程实例
     * @param key
     * @param businessKey 业务id
     * @return
     */
    @Override
    public String startProcess(String key,String businessKey) {
        //流程变量map
        Map<String,Object> variables = new HashMap<>();
        //设置任务负责人
        variables.put("assignee1","2");
        variables.put("assignee2","3");
        variables.put("assignee3","4");
        ProcessInstance instance = null;
        try {
            instance = runtimeService.startProcessInstanceByKey(key, businessKey,variables);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return instance.getId();
    }


    /**
     * 完成任务
     * @param processInstanceId
     * @param assignee
     * @return
     */
    @Override
    public String completeTask(String processInstanceId, int assignee,int flag) {

//        Task task = taskService.createTaskQuery()
//                .processDefinitionKey(key)
//                .taskAssignee(assingee)
//                .singleResult();
        String assigneeStr = ""+assignee;
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(assigneeStr)
                .singleResult();

        Map<String,Object> map = new HashMap<>();
        map.put("result",flag);
        if (task!=null){
            System.out.println(task);
            taskService.complete(task.getId(),map);
        }

        return null;
    }


}
