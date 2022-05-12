package com.example.service.impl;


import com.example.service.ActivitiService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
