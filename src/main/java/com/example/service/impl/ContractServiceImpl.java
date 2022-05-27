package com.example.service.impl;

import com.example.mapper.ContractMapper;
import com.example.pojo.Contract;
import com.example.pojo.ProjectInfo;
import com.example.service.ContractService;
import com.github.pagehelper.PageHelper;
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
        String pro_name = contract.getPro_name();

        if (pro_name != null && pro_name.length() > 0) {
            contract.setPro_name("%"+pro_name+"%");
        }

        PageHelper.startPage(curPage,pageCount);
        List<Contract> contracts = contractMapper.selectContractByCondition(contract);

        return contracts;
    }

    @Override
    public void updateById(Contract contract) {
        contractMapper.updateById(contract);
    }

    @Override
    public Contract selectContractById(int id) {
        Contract contract = contractMapper.selectById(id);
        return contract;
    }

    @Override
    public Contract selectContractByProNo(String proNo) {
        Contract contract = contractMapper.selectContractByProNo(proNo);
        return contract;
    }

    @Override
    public void addContractPath(int id, String path) {
        contractMapper.addTextPath(id,path);
    }
}
