package com.example.mapper;


import com.example.pojo.CheckProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckProcessMapper {

    void addCheckProcess(CheckProcess checkProcess);


    @Select("select * from check_progress where pro_no=#{proName} and bus_type=#{busiType}")
    CheckProcess getProcessByProNoAndBusiType(@Param("proName") String proName, @Param("busiType") String busiType);

    void updateCheckProcess(CheckProcess checkProcess);



    //在办任务
    List<CheckProcess> selectProcessingTask(String userNo);

}
