package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-13:20
 * @描述 海图作业员个人工作质量评价表
 */
@ApiModel("海图作业员个人工作质量评价表数据模型")
public class wm_quality_makeuser {
    @ApiModelProperty("唯一ID")
    private String quser_id;
    @ApiModelProperty("任务ID")
    private String task_index_id;
    @ApiModelProperty("部门ID")
    private String dept_id;
    @ApiModelProperty("用户ID")
    private String user_id;
    @ApiModelProperty("部门名称")
    private String dept_name;
    @ApiModelProperty("用户名称")
    private String user_name;
    @ApiModelProperty("年份")
    private String task_year;
    @ApiModelProperty("季度")
    private String task_quarter;
    @ApiModelProperty("产品分类（数字图，纸图，S57）")
    private String product_type;
    @ApiModelProperty("图号")
    private String chart_code;
    @ApiModelProperty("图名")
    private String chart_name;
    @ApiModelProperty("错漏(每工天)")
    private String chart_error;
    @ApiModelProperty("质量评定（优秀合格）")
    private String chart_result;
    @ApiModelProperty("工天")
    private String work_days;

    public String getQuser_id() {
        return quser_id;
    }

    public void setQuser_id(String quser_id) {
        this.quser_id = quser_id;
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

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTask_year() {
        return task_year;
    }

    public void setTask_year(String task_year) {
        this.task_year = task_year;
    }

    public String getTask_quarter() {
        return task_quarter;
    }

    public void setTask_quarter(String task_quarter) {
        this.task_quarter = task_quarter;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getChart_code() {
        return chart_code;
    }

    public void setChart_code(String chart_code) {
        this.chart_code = chart_code;
    }

    public String getChart_name() {
        return chart_name;
    }

    public void setChart_name(String chart_name) {
        this.chart_name = chart_name;
    }

    public String getChart_error() {
        return chart_error;
    }

    public void setChart_error(String chart_error) {
        this.chart_error = chart_error;
    }

    public String getChart_result() {
        return chart_result;
    }

    public void setChart_result(String chart_result) {
        this.chart_result = chart_result;
    }

    public String getWork_days() {
        return work_days;
    }

    public void setWork_days(String work_days) {
        this.work_days = work_days;
    }
}
