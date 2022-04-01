package com.example.pojo;

public class Menu {
    private Integer id;
    private String name;//导航名称
    private String state;//默认打开还是关闭
    private String iconCls;//导航图标
    private Integer parentId;//父级id
//    private Float sort;//排序字段
    private String description;//导航说明

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
//
//    public Float getSort() {
//        return sort;
//    }
//
//    public void setSort(Float sort) {
//        this.sort = sort;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", text='" + name + '\'' +
                ", state='" + state + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", parentId=" + parentId +
                ", description='" + description + '\'' +
                '}';
    }
}
