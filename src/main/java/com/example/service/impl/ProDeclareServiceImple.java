package com.example.service.impl;

import com.example.mapper.ProDeclareMapper;
import com.example.pojo.Dept;
import com.example.pojo.ProDeclare;
import com.example.service.ProDeclareService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProDeclareServiceImple implements ProDeclareService {

    @Autowired
    private ProDeclareMapper proDeclareMapper;

    @Override
    public List<ProDeclare> selectProDeclareByCondition(ProDeclare proDeclare, int curPage, int pageCount) {
        String pro_name = proDeclare.getPro_name();

        if (pro_name != null && pro_name.length() > 0) {
            proDeclare.setPro_name("%"+pro_name+"%");
        }

        PageHelper.startPage(curPage,pageCount);
        List<ProDeclare> proDeclares = proDeclareMapper.selectProDeclareByCondition(proDeclare);

        return proDeclares;

    }

    @Override
    public ProDeclare selectProDeclareById(int id) {

        ProDeclare proDeclare = proDeclareMapper.selectById(id);
        return proDeclare;
    }

    @Override
    public ProDeclare selectProDeclareByNo(String proNo) {
        ProDeclare proDeclare = proDeclareMapper.selectByNo(proNo);
        return proDeclare;
    }

    @Override
    public void updateStateById(int id, String state) {
        proDeclareMapper.updateStateById(id,state);
    }
}
