package com.startest.wm.pojo;

import com.startest.wm.model.ProjectIndexModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("科研项目索引信息对象")
public class wm_project_index {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("父ID")
    private String pid;
    @ApiModelProperty("项目名称")
    private String index_name;
    @ApiModelProperty("顺序")
    private Integer index_order;
    @ApiModelProperty("子类")
    private List<ProjectIndexModel> children;

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

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public Integer getIndex_order() {
        return index_order;
    }

    public void setIndex_order(Integer index_order) {
        this.index_order = index_order;
    }

    public List<ProjectIndexModel> getChildren() {
        return children;
    }

    public void setChildren(List<ProjectIndexModel> children) {
        this.children = children;
    }
}
