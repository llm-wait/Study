package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-26-14:08
 * @描述 项目科目信息对象
 */
@ApiModel("项目科目信息对象")
public class wm_project_subject {
    @ApiModelProperty("科目ID")
    private String subject_id;
    @ApiModelProperty("项目ID")
    private String project_id;
    @ApiModelProperty("科目名称")
    private String subject_name;
    @ApiModelProperty("预算金额")
    private String subject_budget;
    @ApiModelProperty("到账金额")
    private String subject_received;
    @ApiModelProperty("支出金额")
    private String subject_pay;
    @ApiModelProperty("剩余金额")
    private String subject_balance;
    @ApiModelProperty("标准")
    private String subject_standard;
    @ApiModelProperty("数量")
    private String subject_count;
    @ApiModelProperty("主要用途")
    private String subject_use;
    @ApiModelProperty("详细说明")
    private String subject_describe;
    @ApiModelProperty("备注")
    private String remark;

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_budget() {
        return subject_budget;
    }

    public void setSubject_budget(String subject_budget) {
        this.subject_budget = subject_budget;
    }

    public String getSubject_received() {
        return subject_received;
    }

    public void setSubject_received(String subject_received) {
        this.subject_received = subject_received;
    }

    public String getSubject_pay() {
        return subject_pay;
    }

    public void setSubject_pay(String subject_pay) {
        this.subject_pay = subject_pay;
    }

    public String getSubject_balance() {
        return subject_balance;
    }

    public void setSubject_balance(String subject_balance) {
        this.subject_balance = subject_balance;
    }

    public String getSubject_standard() {
        return subject_standard;
    }

    public void setSubject_standard(String subject_standard) {
        this.subject_standard = subject_standard;
    }

    public String getSubject_count() {
        return subject_count;
    }

    public void setSubject_count(String subject_count) {
        this.subject_count = subject_count;
    }

    public String getSubject_use() {
        return subject_use;
    }

    public void setSubject_use(String subject_use) {
        this.subject_use = subject_use;
    }

    public String getSubject_describe() {
        return subject_describe;
    }

    public void setSubject_describe(String subject_describe) {
        this.subject_describe = subject_describe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
