package com.example.mapper;
import com.example.pojo.Dept;
import com.example.pojo.Flow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface EventMapper {

    /**
     * 流程查询
     * @param flow
     * @return
     */
    List<Flow>selectFlowByCondition(Flow flow);

    /**
     * 根据id查询流程
     * @param id
     * @return
     */
    Flow selectFlowById(int id);


    /**
     * 修改状态
     * @param id
     * @param state
     */
    void updateFlowById(@Param("id") int id, @Param("state") int state);


}
