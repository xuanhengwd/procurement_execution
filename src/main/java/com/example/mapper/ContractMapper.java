package com.example.mapper;

import com.example.pojo.Contract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {

    /**
     * 新增合同信息
     * @param contract
     */
    void addContract(Contract contract);

    /**
     * 条件查询合同
     * @param contract
     * @return
     */
    List<Contract> selectContractByCondition(Contract contract);


}
