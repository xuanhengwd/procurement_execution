package com.example.service;

import com.example.pojo.ProDeclare;

import java.util.List;


/**
 * 采购申报相关业务层接口
 */
public interface ProDeclareService {
    /**
     * 查询所有的采购申报信息
     * @param proDeclare
     * @param curPage
     * @param pageCount
     * @return
     */
    List<ProDeclare> selectProDeclareByCondition(ProDeclare proDeclare,int curPage,int pageCount);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    ProDeclare selectProDeclareById(int id);

    /**
     * 根据编号查询
     * @param proNo
     * @return
     */
    ProDeclare selectProDeclareByNo(String proNo);

    /**
     * 修改状态
     * @param id
     * @param state
     */
    void updateStateById(int id,String state);
}
