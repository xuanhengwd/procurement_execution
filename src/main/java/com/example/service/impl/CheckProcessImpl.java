package com.example.service.impl;

import com.example.mapper.CheckProcessMapper;
import com.example.pojo.CheckProcess;
import com.example.pojo.ProDeclare;
import com.example.service.CheckProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CheckProcessImpl implements CheckProcessService {

    @Autowired
    private CheckProcessMapper checkProcessMapper;

    @Override
    public void createProcess(ProDeclare proDeclare, String busiType) {
        CheckProcess checkProcess = new CheckProcess();
        checkProcess.setPro_no(proDeclare.getPro_no());
        checkProcess.setPro_name(proDeclare.getPro_name());
        checkProcess.setBus_type(busiType);
        checkProcess.setApplicant_no(proDeclare.getApplicant_no());
        checkProcess.setApplicant(proDeclare.getApplicant_name());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDate = sdf.format(new Date());

        checkProcess.setAudit_date(curDate);
        checkProcess.setAudit_state("1");
        checkProcess.setAudit_process("0");
        checkProcessMapper.addCheckProcess(checkProcess);
    }

    @Override
    public CheckProcess getProcessByProNoAndBusiType(String proNo, String busiType) {
        CheckProcess checkProcess = checkProcessMapper.getProcessByProNoAndBusiType(proNo, busiType);
        return checkProcess;
    }

    @Override
    public void updateCheckProcess(CheckProcess checkProcess) {
        checkProcessMapper.updateCheckProcess(checkProcess);
    }

    @Override
    public List<CheckProcess> getProcessingTask(String userNo) {
        List<CheckProcess> checkProcesses = checkProcessMapper.selectProcessingTask(userNo);
        return checkProcesses;
    }
}
