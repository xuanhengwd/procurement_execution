package com.example.mapper;


import com.example.pojo.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectInfoMapper {


    /**
     * 条件查询
     * @param projectInfo
     * @return
     */
    List<ProjectInfo> selectProjectInfoByCondition(ProjectInfo projectInfo);

    ProjectInfo selectById(int id);

    /**
     * 添加
     * @param projectInfo
     */
    void addProjectInfo(ProjectInfo projectInfo);


    void updateProjectInfo(ProjectInfo projectInfo);
}
