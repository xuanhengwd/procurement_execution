package com.example;


import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ProexecuBackApplicationTests {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;


    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void contextLoads() {
        //部署
        Deployment deploy = repositoryService.createDeployment()
                .name("请假申请")
                .addClasspathResource("bpmn/test.bpmn20.xml")
                .deploy();

        //输出部署信息
        System.out.println("流程部署id：" + deploy.getId());
        System.out.println("流程部署名字：" + deploy.getName());
        System.out.println("流程定义key"+deploy.getKey());

    }


    //查询部署
    @Test
    void searchDeploy()
    {
        Deployment biding = repositoryService.createDeploymentQuery()
                .processDefinitionKey("biding")
                .singleResult();

        System.out.println(biding.getId());

    }


    //开启流程实例
    @Test
    public void testStartProcess(){
        String key="Myprocess";
        //流程变量map
        Map<String,Object> variables = new HashMap<>();
        //设置任务负责人
        variables.put("id1","1");
        variables.put("id2","2");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key, variables);

        System.out.println("流程定义id："+instance.getProcessDefinitionId());
        System.out.println("流程实例id："+instance.getId());
        System.out.println("当前活动的id："+instance.getActivityId());

    }



    @Test
    public void completTask(){

        String processInstanceId="60001";
        String key="Myprocess";
        String assingee="2";
        Task task = taskService.createTaskQuery()
                .taskAssignee(assingee)
                .singleResult();
        Map<String,Object> map = new HashMap<>();
        map.put("result",0);
        if (task!=null){
            taskService.complete(task.getId(),map);
        }
    }


    /**
     * 查看历史信息
     */
    @Test
    public void findHistoryInfo(){

        //获取actinst表的查询对象
        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();

        instanceQuery.processInstanceId("491f3913-cedd-11ec-be17-525bc2c00c45");
        //查询actinst表  条件：根据InstanceId 查询
        //instanceQuery.processInstanceId("10001");
        //根据DefinitionId
        //增加排序
        instanceQuery.orderByHistoricActivityInstanceStartTime().asc();
        List<HistoricActivityInstance> activityInstanceList = instanceQuery.list();

        for (HistoricActivityInstance instance : activityInstanceList) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance.getProcessInstanceId());
            System.out.println("----------------------");
        }

    }

    /**
     * 删除
     */
    @Test
    public void deleteDeployMent(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //通过部署id删除流程部署信息
        String deploymentId="4b22509a-d06f-11ec-8c3b-525bc2c00c45";
        //repositoryService.deleteDeployment(deploymentId);

        repositoryService.deleteDeployment(deploymentId,true);

    }


    @Test
    public void deleteDeployMent1(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //通过部署id删除流程部署信息
        String[] deploymentIds={
                "479368f2-cedd-11ec-be17-525bc2c00c45",
                "768e6b5c-ceda-11ec-a5c9-525bc2c00c45",
                "81a6979f-ced4-11ec-94fa-525bc2c00c45",
                "a381162c-cf97-11ec-9801-525bc2c00c45",
                "e4094a21-ced6-11ec-b7e3-525bc2c00c45",
                "fcd9a2cc-ced5-11ec-b50b-525bc2c00c45"
        };
        //repositoryService.deleteDeployment(deploymentId);
        for (String deploymentId : deploymentIds) {
            repositoryService.deleteDeployment(deploymentId,true);
        }


    }


    @Test
    public void test(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        System.out.println("=>>>>>>>>>"+format);

    }

    @Test
    public void tes2(){


        System.out.println(encoder.encode("admin"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("test1"));
    }


}
