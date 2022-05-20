package com.example.service;

import com.example.pojo.CheckProcess;
import com.example.pojo.ProDeclare;

public interface CheckProcessService {

    public void createProcess(ProDeclare proDeclare, String busiType);

    public CheckProcess getProcessByProNoAndBusiType(String proNo, String busiType);
    public void updateCheckProcess(CheckProcess checkProcess);
}
