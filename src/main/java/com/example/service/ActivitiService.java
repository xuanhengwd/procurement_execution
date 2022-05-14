package com.example.service;


import org.springframework.stereotype.Service;

@Service
public interface ActivitiService {


    /**
     * 部署
     * @param name
     * @param key
     * @param path
     * @return
     */
    String deployment(String name,String key,String path);


    /**
     * 获取部署id
     * @param key
     * @return
     */
    String getDeployIdByKey(String key);


    /**
     * 删除部署
     * @param deployId
     * @return
     */
    String deleteDeployment(String deployId);


    /**
     * 开启任务
     * @param key
     * @return
     */
    String startProcess(String key,String businessKey);


    /**
     * 完成任务
     * @param processInstanceId
     * @param assignee
     * @return
     */
    String completeTask(String processInstanceId,int assignee,int flag);

}
