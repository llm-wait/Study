package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-22-16:32
 * @描述 个人工作质量评价表
 */
@ApiModel("校对审查个人工作质量评价数据模型")
public class wm_quality_user {
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
    @ApiModelProperty("产品类型（海图、书表等）")
    private String product_type;
    @ApiModelProperty("产品分类（数字图、纸质图、S57）")
    private String product_class;
    @ApiModelProperty("图书号")
    private String product_code;
    @ApiModelProperty("工天")
    private String user_workdays;
    @ApiModelProperty("平均工天")
    private String average_workdays;
    @ApiModelProperty("工天因数")
    private String workdays_factor;
    @ApiModelProperty("消灭错漏率")
    private String remove_error_rate;
    @ApiModelProperty("标准成绩")
    private String standard_score;
    @ApiModelProperty("质量评定（优秀，良好，合格）")
    private String performance_evaluation;

    @ApiModelProperty("季度")
    private String quarter;

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

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

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_class() {
        return product_class;
    }

    public void setProduct_class(String product_class) {
        this.product_class = product_class;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getUser_workdays() {
        return user_workdays;
    }

    public void setUser_workdays(String user_workdays) {
        this.user_workdays = user_workdays;
    }

    public String getAverage_workdays() {
        return average_workdays;
    }

    public void setAverage_workdays(String average_workdays) {
        this.average_workdays = average_workdays;
    }

    public String getWorkdays_factor() {
        return workdays_factor;
    }

    public void setWorkdays_factor(String workdays_factor) {
        this.workdays_factor = workdays_factor;
    }

    public String getRemove_error_rate() {
        return remove_error_rate;
    }

    public void setRemove_error_rate(String remove_error_rate) {
        this.remove_error_rate = remove_error_rate;
    }

    public String getStandard_score() {
        return standard_score;
    }

    public void setStandard_score(String standard_score) {
        this.standard_score = standard_score;
    }

    public String getPerformance_evaluation() {
        return performance_evaluation;
    }

    public void setPerformance_evaluation(String performance_evaluation) {
        this.performance_evaluation = performance_evaluation;
    }

    @Override
    public String toString() {
        return "wm_quality_user{" +
                "quser_id='" + quser_id + '\'' +
                ", task_index_id='" + task_index_id + '\'' +
                ", dept_id='" + dept_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", task_year='" + task_year + '\'' +
                ", product_type='" + product_type + '\'' +
                ", product_class='" + product_class + '\'' +
                ", product_code='" + product_code + '\'' +
                ", user_workdays='" + user_workdays + '\'' +
                ", average_workdays='" + average_workdays + '\'' +
                ", workdays_factor='" + workdays_factor + '\'' +
                ", remove_error_rate='" + remove_error_rate + '\'' +
                ", standard_score='" + standard_score + '\'' +
                ", performance_evaluation='" + performance_evaluation + '\'' +
                ", quarter='" + quarter + '\'' +
                '}';
    }
}
