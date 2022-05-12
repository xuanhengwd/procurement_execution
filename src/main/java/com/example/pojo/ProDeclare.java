package com.example.pojo;

/**
 * 采购申报
 */
public class ProDeclare {
    private Integer id;
    private String pro_no;
    private String pro_name;
    private String funds_no;
    private String funds_name;

    private String funds_type;

    private String dept_no;
    private String dept_name;
    private String applicant_no;
    private String applicant_name;
    private String tel;
    private String app_date;
    private String state;//审核状态

    private String app_category_no;
    private String app_category_name;
    private String apply_reason;
    private String budget;
    private String dept_principal_no;
    private String dept_principal_name;
    private String pro_principal_name;
    private String pro_principal_no;
    private String fund_source;
    private String budget_year;
    private String remark;
    private String Zfzd1;
    private String Szzd1;
    private String Rqzd1;

    @Override
    public String toString() {
        return "ProDeclare{" +
                "id=" + id +
                ", pro_no='" + pro_no + '\'' +
                ", pro_name='" + pro_name + '\'' +
                ", funds_no='" + funds_no + '\'' +
                ", funds_name='" + funds_name + '\'' +
                ", funds_type='" + funds_type + '\'' +
                ", dept_no='" + dept_no + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", applicant_no='" + applicant_no + '\'' +
                ", applicant_name='" + applicant_name + '\'' +
                ", tel='" + tel + '\'' +
                ", app_date='" + app_date + '\'' +
                ", state='" + state + '\'' +
                ", app_category_no='" + app_category_no + '\'' +
                ", app_category_name='" + app_category_name + '\'' +
                ", apply_reason='" + apply_reason + '\'' +
                ", budget='" + budget + '\'' +
                ", dept_principal_no='" + dept_principal_no + '\'' +
                ", dept_principal_name='" + dept_principal_name + '\'' +
                ", pro_principal_name='" + pro_principal_name + '\'' +
                ", pro_principal_no='" + pro_principal_no + '\'' +
                ", fund_source='" + fund_source + '\'' +
                ", budget_year='" + budget_year + '\'' +
                ", remark='" + remark + '\'' +
                ", Zfzd1='" + Zfzd1 + '\'' +
                ", Szzd1='" + Szzd1 + '\'' +
                ", Rqzd1='" + Rqzd1 + '\'' +
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

    public String getFunds_no() {
        return funds_no;
    }

    public void setFunds_no(String funds_no) {
        this.funds_no = funds_no;
    }

    public String getFunds_name() {
        return funds_name;
    }

    public void setFunds_name(String funds_name) {
        this.funds_name = funds_name;
    }

    public String getFunds_type() {
        return funds_type;
    }

    public void setFunds_type(String funds_type) {
        this.funds_type = funds_type;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getApplicant_no() {
        return applicant_no;
    }

    public void setApplicant_no(String applicant_no) {
        this.applicant_no = applicant_no;
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getApp_date() {
        return app_date;
    }

    public void setApp_date(String app_date) {
        this.app_date = app_date;
    }

    public String getState() {
        return state;
    }

    public String getStateStr(){
        if (state == null){
            return "未知";
        }else if("0".equals(state)){
            return "未审";
        }else if("1".equals(state)){
            return "审核中";
        }else if("2".equals(state)){
            return "审核完结";
        }else if("3".equals(state)){
            return "驳回";
        }else if("4".equals(state)){
            return "采购执行";
        }else if("5".equals(state)){
            return "合同签订";
        }else{
            return "到货验货";
        }
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getApp_category_no() {
        return app_category_no;
    }

    public void setApp_category_no(String app_category_no) {
        this.app_category_no = app_category_no;
    }

    public String getApp_category_name() {
        return app_category_name;
    }

    public void setApp_category_name(String app_category_name) {
        this.app_category_name = app_category_name;
    }

    public String getApply_reason() {
        return apply_reason;
    }

    public void setApply_reason(String apply_reason) {
        this.apply_reason = apply_reason;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDept_principal_no() {
        return dept_principal_no;
    }

    public void setDept_principal_no(String dept_principal_no) {
        this.dept_principal_no = dept_principal_no;
    }

    public String getDept_principal_name() {
        return dept_principal_name;
    }

    public void setDept_principal_name(String dept_principal_name) {
        this.dept_principal_name = dept_principal_name;
    }

    public String getPro_principal_name() {
        return pro_principal_name;
    }

    public void setPro_principal_name(String pro_principal_name) {
        this.pro_principal_name = pro_principal_name;
    }

    public String getPro_principal_no() {
        return pro_principal_no;
    }

    public void setPro_principal_no(String pro_principal_no) {
        this.pro_principal_no = pro_principal_no;
    }

    public String getFund_source() {
        return fund_source;
    }

    public void setFund_source(String fund_source) {
        this.fund_source = fund_source;
    }

    public String getBudget_year() {
        return budget_year;
    }

    public void setBudget_year(String budget_year) {
        this.budget_year = budget_year;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZfzd1() {
        return Zfzd1;
    }

    public void setZfzd1(String zfzd1) {
        Zfzd1 = zfzd1;
    }

    public String getSzzd1() {
        return Szzd1;
    }

    public void setSzzd1(String szzd1) {
        Szzd1 = szzd1;
    }

    public String getRqzd1() {
        return Rqzd1;
    }

    public void setRqzd1(String rqzd1) {
        Rqzd1 = rqzd1;
    }


}
