package com.example.service.impl;

import com.example.mapper.ContractMapper;
import com.example.pojo.Contract;
import com.example.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper contractMapper;
    @Override
    public void addContract(Contract contract) {
        contractMapper.addContract(contract);

    }

    @Override
    public List<Contract> selectContractByCondition(Contract contract, int curPage, int pageCount) {
        return null;
    }
}
