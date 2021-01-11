package com.startest.wm.model;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-12-21 14:30
 * @描述 科研项目索引模型
 **/
public class ProjectIndexModel {
    private String id;
    private String pid;
    private String label;
    private String type;
    private Integer order;

    private List<ProjectIndexModel>children;

    public ProjectIndexModel(String id, String pid, String label,String type) {
        this.id = id;
        this.pid = pid;
        this.label = label;
        this.type=type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<ProjectIndexModel> getChildren() {
        return children;
    }

    public void setChildren(List<ProjectIndexModel> children) {
        this.children = children;
    }
}
