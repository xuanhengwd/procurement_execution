package com.example.pojo;

public class Dept {
    private Integer id;
    private String deptNo;//部门编号
    private String deptName;//单位名称
    private Integer parentId;//上一级单位编号
    private String deptNature;//单位性质  1.教学 2.科研 3.教辅4.行政 5.后勤 6.其它
    private String remarks;//单位标志      指单位的标志位，‘*’表示属于最低一级单位。

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDeptNature() {
        return deptNature;
    }

    public void setDeptNature(String deptNature) {
        this.deptNature = deptNature;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                ", parentId=" + parentId +
                ", deptNature='" + deptNature + '\'' +
                ", deptSign='" + remarks + '\'' +
                '}';
    }
}
