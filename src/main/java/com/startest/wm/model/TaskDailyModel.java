package com.startest.wm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-11-18 16:16
 * @描述 日常任务查询结果模型
 **/
public class TaskDailyModel {
    @ApiModelProperty("日常任务ID")
    @JsonProperty(value = "ctask_id")
    private String ctask_id;
    @ApiModelProperty("部门ID")
    @JsonProperty(value = "dept_id")
    private String dept_id;
    @ApiModelProperty("人员ID")
    private String user_id;
    @ApiModelProperty("人员姓名")
    private String user_name;
    @ApiModelProperty("部门名称")
    private String dept_name;
    @ApiModelProperty("任务年份")
    private String ctask_year;
    @ApiModelProperty("任务名称")
    private String ctask_name;
    @ApiModelProperty("人员姓名")
    private String ctask_oper;
    @ApiModelProperty("任务类别")
    private String ctask_type;
    @ApiModelProperty("任务工天")
    private String ctask_workdays;
    @ApiModelProperty("任务备注")
    private String remark;

    public String getCtask_id() {
        return ctask_id;
    }

    public void setCtask_id(String ctask_id) {
        this.ctask_id = ctask_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getCtask_year() {
        return ctask_year;
    }

    public void setCtask_year(String ctask_year) {
        this.ctask_year = ctask_year;
    }

    public String getCtask_name() {
        return ctask_name;
    }

    public void setCtask_name(String ctask_name) {
        this.ctask_name = ctask_name;
    }

    public String getCtask_oper() {
        return ctask_oper;
    }

    public void setCtask_oper(String ctask_oper) {
        this.ctask_oper = ctask_oper;
    }

    public String getCtask_type() {
        return ctask_type;
    }

    public void setCtask_type(String ctask_type) {
        this.ctask_type = ctask_type;
    }

    public String getCtask_workdays() {
        return ctask_workdays;
    }

    public void setCtask_workdays(String ctask_workdays) {
        this.ctask_workdays = ctask_workdays;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
