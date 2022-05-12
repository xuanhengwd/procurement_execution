package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("ActivitiController")
@RequestMapping("/act")
@CrossOrigin
public class ActivitiController {


//    @Autowired
//    private RepositoryService repositoryService;
//
//    @Autowired
//    private RuntimeService runtimeService;
//    //流程部署
//    @RequestMapping("/deploy")
//    @ResponseBody
//    List<Dept> deploy(int curPage, int pageCount) {
//        //部署
//        Deployment deploy = repositoryService.createDeployment()
//                .name("采购执行申请")
//                .addClasspathResource("bpmn/Myprocess.bpmn")
//                .deploy();
//
//        //输出部署信息
//        System.out.println("流程部署id：" + deploy.getId());
//        System.out.println("流程部署名字：" + deploy.getName());
//        System.out.println("流程定义key"+deploy.getKey());
//    }
}
