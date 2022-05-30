package com.example.mapper;


import com.example.pojo.ProDeclare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProDeclareMapper {

    /**
     * 条件查询
     * @param proDeclare
     * @return
     */
    List<ProDeclare> selectProDeclareByCondition(ProDeclare proDeclare);

    //根据id查询
    ProDeclare selectById(int id);

    ProDeclare selectByNo(String proNo);

    void updateStateById(@Param("id") int id, @Param("state") String state);


    @Select("select * from pro_declare")
    List<ProDeclare> selectProDeclareAll();

}
