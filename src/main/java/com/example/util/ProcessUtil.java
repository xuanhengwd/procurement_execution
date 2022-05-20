package com.example.util;

import com.example.mapper.CheckProcessMapper;
import com.example.pojo.CheckProcess;
import com.example.pojo.ProDeclare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ProcessUtil {

//    @Autowired
//    private static CheckProcessMapper checkProcessMapper;
//
//    public static void createProcess(ProDeclare proDeclare,String busiType){
//        CheckProcess checkProcess = new CheckProcess();
//        checkProcess.setPro_no(proDeclare.getPro_no());
//        checkProcess.setPro_name(proDeclare.getPro_name());
//        checkProcess.setBus_type(busiType);
//        checkProcess.setApplicant_id(proDeclare.getId());
//        checkProcess.setApplicant(proDeclare.getApplicant_name());
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String curDate = sdf.format(new Date());
//
//        checkProcess.setAudit_date(curDate);
//        checkProcess.setAudit_state("1");
//        checkProcess.setAudit_process("0");
//        checkProcessMapper.addCheckProcess(checkProcess);
//
//    }
}
