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
}
