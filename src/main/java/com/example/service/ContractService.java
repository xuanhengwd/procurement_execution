package com.example.service;

import com.example.pojo.Contract;

import java.util.List;

public interface ContractService {
    /**
     * 添加合同信息
     */
    void addContract(Contract contract);


    /**
     * 条件查询
     * @param contract
     * @return
     */
    List<Contract> selectContractByCondition(Contract contract,int curPage,int pageCount);
}
