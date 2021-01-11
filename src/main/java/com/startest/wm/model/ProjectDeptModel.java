package com.startest.wm.model;

import com.startest.wm.pojo.wm_project_dept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-15:56
 * @描述 项目分配部门模型
 */
@ApiModel("项目分配部门模型")
public class ProjectDeptModel {
    @ApiModelProperty("项目ID")
    private String project_id;
    @ApiModelProperty("项目分配部门列表")
    private List<wm_project_dept> list;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public List<wm_project_dept> getList() {
        return list;
    }

    public void setList(List<wm_project_dept> list) {
        this.list = list;
    }
}
