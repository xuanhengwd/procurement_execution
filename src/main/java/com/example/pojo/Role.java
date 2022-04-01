package com.example.pojo;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private Integer id;
    private String name;
    private String description;
//    private Float sortNo;
//    private String indexPage;
//    private Integer priority;

    //菜单
//    private List<Menu> menus = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
