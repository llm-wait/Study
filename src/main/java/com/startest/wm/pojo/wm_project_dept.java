package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-24-13:33
 * @描述 项目分配部门
 */
@ApiModel("项目分配部门")
public class wm_project_dept {
    @ApiModelProperty("唯一ID")
    private String id;
    @ApiModelProperty("项目ID")
    private String project_id;
    @ApiModelProperty("部门ID")
    private String dept_id;
    @ApiModelProperty("项目人员分配信息")
    private List<wm_project_distribution> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public List<wm_project_distribution> getList() {
        return list;
    }

    public void setList(List<wm_project_distribution> list) {
        this.list = list;
    }
}
