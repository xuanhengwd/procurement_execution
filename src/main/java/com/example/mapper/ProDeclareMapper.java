package com.example.mapper;


import com.example.pojo.ProDeclare;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProDeclareMapper {

    List<ProDeclare> selectProDeclareByCondition(ProDeclare proDeclare);
}
