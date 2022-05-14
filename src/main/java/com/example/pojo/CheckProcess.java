package com.example.pojo;

public class CheckProcess {

    private Integer id;

    private String pro_no;
    private String bus_type;

    private String auditor_no;
    private String auditor;
    private String audit_date;
    private String audit_opinion;
    private String audit_state;

    @Override
    public String toString() {
        return "CheckProcess{" +
                "id=" + id +
                ", pro_no='" + pro_no + '\'' +
                ", bus_type='" + bus_type + '\'' +
                ", auditor_no='" + auditor_no + '\'' +
                ", auditor='" + auditor + '\'' +
                ", audit_date='" + audit_date + '\'' +
                ", audit_opinion='" + audit_opinion + '\'' +
                ", audit_state='" + audit_state + '\'' +
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

    public String getBus_type() {
        return bus_type;
    }

    public void setBus_type(String bus_type) {
        this.bus_type = bus_type;
    }

    public String getAuditor_no() {
        return auditor_no;
    }

    public void setAuditor_no(String auditor_no) {
        this.auditor_no = auditor_no;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
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

    public void setAudit_state(String audit_state) {
        this.audit_state = audit_state;
    }
}
