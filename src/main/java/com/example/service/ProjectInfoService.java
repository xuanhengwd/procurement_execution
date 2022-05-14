package com.example.service;


import com.example.pojo.ProDeclare;
import com.example.pojo.ProjectInfo;

import java.util.List;

public interface ProjectInfoService {


    /**
     * 插入
     * @param projectInfo
     */
    void addProjectInfo(ProjectInfo projectInfo);
    /**
     * 查询所有的采购申报信息
     * @param projectInfo
     * @param curPage
     * @param pageCount
     * @return
     */
    List<ProjectInfo> selectProjectInfoByCondition(ProjectInfo projectInfo, int curPage, int pageCount);


    /**
     * 更新
     * @param projectInfo
     */
    void updateProjectInfo(ProjectInfo projectInfo);

    ProjectInfo selectProjectInfoById(int id);

}
