package com.example.mapper;

import com.example.pojo.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    @Select("select * from G_DHCD")
    List<Menu> selectMenuAll();

    /**
     * 添加部门
     * @param menu
     */
    void addMenu(Menu menu);

    /**
     * 删除
     * @param id
     */
    @Delete("delete from G_DHCD where id=#{id}")
    void deleteMenuById(int id);


}
