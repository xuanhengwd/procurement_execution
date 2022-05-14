package com.example.pojo;

//项目信息表
public class ProjectInfo {

    private Integer id;
    private String pro_no;
    private String pro_name;
    private String contract_no;
    private String contract_name;
    private Double bid_price;
    private String bid_winner;
    private String bid_date;
    private String bid_linkman;
    private String bid_tel;
    private String bid_reason;
    private String funds_no;
    private String funds_name;
    private String funds_type;


    private String dept_no;
    private String dept_name;
    private String app_category_no;
    private String app_category_name;
    private String apply_reason;
    private Double budget;
    private String dept_principal_no;
    private String dept_principal_name;
    private String pro_principal_name;
    private String pro_principal_no;
    private String fund_source;
    private String budget_year;
    private String remark;
    private String pur_typeno;
    private String pur_typename;
    private String pru_biddocu;
    private String pru_contractdocu;
    private String pru_operno;
    private String pru_opername;
    private String oper_date;
    private String remark1;
    private String Zfzd1;
    private String Szzd1;
    private String Rqzd1;

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "id=" + id +
                ", pro_no='" + pro_no + '\'' +
                ", pro_name='" + pro_name + '\'' +
                ", contract_no='" + contract_no + '\'' +
                ", contract_name='" + contract_name + '\'' +
                ", bid_price=" + bid_price +
                ", bid_winner='" + bid_winner + '\'' +
                ", bid_date='" + bid_date + '\'' +
                ", bid_linkman='" + bid_linkman + '\'' +
                ", bid_tel='" + bid_tel + '\'' +
                ", bid_reason='" + bid_reason + '\'' +
                ", funds_no='" + funds_no + '\'' +
                ", funds_name='" + funds_name + '\'' +
                ", funds_type='" + funds_type + '\'' +
                ", dept_no='" + dept_no + '\'' +
                ", dept_name='" + dept_name + '\'' +
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
                ", pur_typeno='" + pur_typeno + '\'' +
                ", pur_typename='" + pur_typename + '\'' +
                ", pru_biddocu='" + pru_biddocu + '\'' +
                ", pru_contractdocu='" + pru_contractdocu + '\'' +
                ", pru_operno='" + pru_operno + '\'' +
                ", pru_opername='" + pru_opername + '\'' +
                ", oper_date='" + oper_date + '\'' +
                ", remark1='" + remark1 + '\'' +
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

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public Double getBid_price() {
        return bid_price;
    }

    public void setBid_price(Double bid_price) {
        this.bid_price = bid_price;
    }

    public String getBid_winner() {
        return bid_winner;
    }

    public void setBid_winner(String bid_winner) {
        this.bid_winner = bid_winner;
    }

    public String getBid_date() {
        return bid_date;
    }

    public void setBid_date(String bid_date) {
        this.bid_date = bid_date;
    }

    public String getBid_linkman() {
        return bid_linkman;
    }

    public void setBid_linkman(String bid_linkman) {
        this.bid_linkman = bid_linkman;
    }

    public String getBid_tel() {
        return bid_tel;
    }

    public void setBid_tel(String bid_tel) {
        this.bid_tel = bid_tel;
    }

    public String getBid_reason() {
        return bid_reason;
    }

    public void setBid_reason(String bid_reason) {
        this.bid_reason = bid_reason;
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
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

    public String getPur_typeno() {
        return pur_typeno;
    }

    public void setPur_typeno(String pur_typeno) {
        this.pur_typeno = pur_typeno;
    }

    public String getPur_typename() {
        return pur_typename;
    }

    public void setPur_typename(String pur_typename) {
        this.pur_typename = pur_typename;
    }

    public String getPru_biddocu() {
        return pru_biddocu;
    }

    public void setPru_biddocu(String pru_biddocu) {
        this.pru_biddocu = pru_biddocu;
    }

    public String getPru_contractdocu() {
        return pru_contractdocu;
    }

    public void setPru_contractdocu(String pru_contractdocu) {
        this.pru_contractdocu = pru_contractdocu;
    }

    public String getPru_operno() {
        return pru_operno;
    }

    public void setPru_operno(String pru_operno) {
        this.pru_operno = pru_operno;
    }

    public String getPru_opername() {
        return pru_opername;
    }

    public void setPru_opername(String pru_opername) {
        this.pru_opername = pru_opername;
    }

    public String getOper_date() {
        return oper_date;
    }

    public void setOper_date(String oper_date) {
        this.oper_date = oper_date;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
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
