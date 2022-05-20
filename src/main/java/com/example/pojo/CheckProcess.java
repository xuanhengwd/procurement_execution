package com.example.pojo;

public class CheckProcess {

    private Integer id;

    private String pro_no;
    private String pro_name;
    private String bus_type;

    private Integer applicant_id;
    private String applicant;
    private String audit_date;
    private String audit_opinion;
    private String audit_state;
    private String audit_process;

    @Override
    public String toString() {
        return "CheckProcess{" +
                "id=" + id +
                ", pro_no='" + pro_no + '\'' +
                ", pro_name='" + pro_name + '\'' +
                ", bus_type='" + bus_type + '\'' +
                ", applicant_id=" + applicant_id +
                ", applicant='" + applicant + '\'' +
                ", audit_date='" + audit_date + '\'' +
                ", audit_opinion='" + audit_opinion + '\'' +
                ", audit_state='" + audit_state + '\'' +
                ", audit_process='" + audit_process + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPro_no() {
        return pro_no;
    }

    public void setPro_no(String pro_no) {
        this.pro_no = pro_no;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getBus_type() {
        return bus_type;
    }
    public String getBus_typeStr(){
        if("1".equals(bus_type)){
            return "招标审核";
        }else if("2".equals(bus_type)){
            return "中标审核";
        }else if("3".equals(bus_type)){
            return "明细审核";
        }else if("4".equals(bus_type)){
            return "合同审核";
        }else {
            return "验收审核";
        }
    }

    public void setBus_type(String bus_type) {
        this.bus_type = bus_type;
    }

    public Integer getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(Integer applicant_id) {
        this.applicant_id = applicant_id;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAudit_date() {
        return audit_date;
    }

    public void setAudit_date(String audit_date) {
        this.audit_date = audit_date;
    }

    public String getAudit_opinion() {
        return audit_opinion;
    }

    public void setAudit_opinion(String audit_opinion) {
        this.audit_opinion = audit_opinion;
    }

    public String getAudit_state() {
        return audit_state;
    }
    public String getAudit_stateStr(){
        if("1".equals(audit_state)){
            return "任务进行";
        }else if("2".equals(audit_state)){
            return "任务成功";
        }else {
            return "任务拒绝";
        }
    }

    public void setAudit_state(String audit_state) {
        this.audit_state = audit_state;
    }

    public String getAudit_process() {
        return audit_process;
    }
    public String getAudit_processStr() {
        if("0".equals(audit_process)){
            return "任务未审核";
        }else if("1".equals(audit_process)){
            return "一审";
        }else if("2".equals(audit_process)){
            return "二审";
        }else if("3".equals(audit_process)){
            return "三审";
        }else {
            return "四审";
        }

    }

    public void setAudit_process(String audit_process) {
        this.audit_process = audit_process;
    }


}
