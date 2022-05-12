package com.example.controller;

import com.example.pojo.ProDeclare;
import com.example.service.ProDeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/proDeclare")
@CrossOrigin
public class ProDeclareController {


    @Autowired
    private ProDeclareService proDeclareService;


    /**
     * 查询
     * @param proDeclare
     * @param curPage
     * @param pageCount
     * @return
     */
    @RequestMapping(value = "/selectProDeclareByCondition", method = RequestMethod.POST)
    @ResponseBody
    List<ProDeclare> selectProDeclareByCondition(ProDeclare proDeclare,int curPage,int pageCount){
        List<ProDeclare> proDeclares = proDeclareService.selectProDeclareByCondition(proDeclare, curPage, pageCount);
        return proDeclares;

    }
}
