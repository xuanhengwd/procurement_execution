package com.example.mapper;


import com.example.pojo.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from project_info where pro_no = #{proNo}")
    ProjectInfo selectByProNo(String proNo);

    /**
     * 添加
     * @param projectInfo
     */
    void addProjectInfo(ProjectInfo projectInfo);

    /**
     *更新
     * @param projectInfo
     */
    void updateProjectInfo(ProjectInfo projectInfo);

}
