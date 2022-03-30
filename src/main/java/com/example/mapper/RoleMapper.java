package com.example.mapper;

import com.example.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    @Select("select * from G_JS")
    List<Role> selectRoleAll();

}
