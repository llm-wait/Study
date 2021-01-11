package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-12-21 10:36
 * @描述
 **/
@ApiModel("科研项目进度信息对象")
public class wm_project_process {
    @ApiModelProperty("唯一ID")
    private String id;
    @ApiModelProperty("项目ID")
    private String project_id;
    @ApiModelProperty("进度名称")
    private String process_name;
    @ApiModelProperty("进度时间")
    private String process_time;
    @ApiModelProperty("备注")
    private String remark;

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

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public String getProcess_time() {
        return process_time;
    }

    public void setProcess_time(String process_time) {
        this.process_time = process_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
