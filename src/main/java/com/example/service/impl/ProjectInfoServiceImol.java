package com.example.service.impl;

import com.example.mapper.ProjectInfoMapper;
import com.example.pojo.ProDeclare;
import com.example.pojo.ProjectInfo;
import com.example.service.ProjectInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectInfoServiceImol implements ProjectInfoService {

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    @Override
    public void addProjectInfo(ProjectInfo projectInfo) {
        projectInfoMapper.addProjectInfo(projectInfo);

    }

    @Override
    public List<ProjectInfo> selectProjectInfoByCondition(ProjectInfo projectInfo, int curPage, int pageCount) {
        String pro_name = projectInfo.getPro_name();

        if (pro_name != null && pro_name.length() > 0) {
            projectInfo.setPro_name("%"+pro_name+"%");
        }

        PageHelper.startPage(curPage,pageCount);
        List<ProjectInfo> projectInfos = projectInfoMapper.selectProjectInfoByCondition(projectInfo);

        return projectInfos;

    }

    @Override
    public void updateProjectInfo(ProjectInfo projectInfo) {
        projectInfoMapper.updateProjectInfo(projectInfo);
    }

    @Override
    public ProjectInfo selectProjectInfoById(int id) {
        ProjectInfo projectInfo = projectInfoMapper.selectById(id);
        return projectInfo;
    }

    @Override
    public ProjectInfo selectProjectInfoByProNo(String proNo) {

        ProjectInfo projectInfo = projectInfoMapper.selectByProNo(proNo);
        return projectInfo;
    }
}
