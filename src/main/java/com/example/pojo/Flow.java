package com.example.pojo;

/**
 * 工作流
 */
public class Flow {
    private Integer id;
    private String flowName;
    private String flowKey;
    private String filepath;
    private Integer state;

    @Override
    public String toString() {
        return "Flow{" +
                "id=" + id +
                ", flow_name='" + flowName + '\'' +
                ", flow_key='" + flowKey + '\'' +
                ", filepath='" + filepath + '\'' +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getState() {
        return state;
    }

    //逻辑视图
    public String getStateStr(){
        if (state == null){
            return "未知";
        }
        return state == 0 ? "禁用":"启用";
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
