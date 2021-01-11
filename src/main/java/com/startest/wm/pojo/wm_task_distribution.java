package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-01-16:23
 * @描述 任务分配表单（任务执行单）
 */

@ApiModel("任务分配表单（任务执行单）")
public class wm_task_distribution {
    @ApiModelProperty("唯一ID")
    private String distribution_id;
    @ApiModelProperty("任务索引id")
    private String task_index_id;
    @ApiModelProperty("部门id")
    private String dept_id;
    @ApiModelProperty("人员id")
    private String user_id;
    @ApiModelProperty("人员姓名")
    private String user_name;
    @ApiModelProperty("执行单类型（海图、书表）")
    private String product_type;
    @ApiModelProperty("图号/书号")
    private String data_code;
    @ApiModelProperty("图名/书名")
    private String data_name;
    @ApiModelProperty("分配内容(海图：数字图，纸图，S57图)(书表：第一章)")
    private String distribution_type;
    @ApiModelProperty("任务职责（编辑设计，作业，组校，校对，编辑审查，验收，报局，入库）")
    private String user_duty;
    @Min(0)
    @ApiModelProperty("分配工天")
    private Double work_days;
    @ApiModelProperty("接受时间")
    private String start_date;
    @ApiModelProperty("完成时间")
    private String end_date;
    @ApiModelProperty("备注")
    private String remark;

    public String getDistribution_id() {
        return distribution_id;
    }

    public void setDistribution_id(String distribution_id) {
        this.distribution_id = distribution_id;
    }

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
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

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getData_code() {
        return data_code;
    }

    public void setData_code(String data_code) {
        this.data_code = data_code;
    }

    public String getData_name() {
        return data_name;
    }

    public void setData_name(String data_name) {
        this.data_name = data_name;
    }

    public String getDistribution_type() {
        return distribution_type;
    }

    public void setDistribution_type(String distribution_type) {
        this.distribution_type = distribution_type;
    }

    public String getUser_duty() {
        return user_duty;
    }

    public void setUser_duty(String user_duty) {
        this.user_duty = user_duty;
    }

    public Double getWork_days() {
        return work_days;
    }

    public void setWork_days(Double work_days) {
        this.work_days = work_days;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
