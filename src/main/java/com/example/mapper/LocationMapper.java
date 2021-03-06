package com.example.mapper;

import com.example.pojo.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {

    /**
     * 查询所有
     * @return
     */
    List<Location> selectLocationAll();

    /**
     * 添加存放位置
     * @param location
     */
    void addLocation(Location location);

    /**
     * 根据id删除
     * @param id
     */
    void deleteLocationById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteLocationByIds(@Param("ids") int[] ids);

    /**
     * 修改
     * @param location
     */
    void updateLocationById(Location location);

    /**
     * 条件查询
     * @param location
     * @return
     */
    List<Location> selectLocationByCondition(Location location);

    /**
     * 存放地数量
     * @return
     */
    int locationCount();
}
