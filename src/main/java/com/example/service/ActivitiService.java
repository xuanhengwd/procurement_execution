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
}
