package com.example.pojo;

//存放地址
public class Address {
    private Integer id;
    private String addressNo;//存放地编号
    private String addressName;//存放地名称
    private String deptNo;//单位编号
    private String deptName;//单位名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressNo='" + addressNo + '\'' +
                ", addressName='" + addressName + '\'' +
                ", deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
