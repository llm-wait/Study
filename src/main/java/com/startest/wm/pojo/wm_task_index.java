package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-01-15:29
 * @描述 任务索引表
 */
@ApiModel("任务索引表对象")
public class wm_task_index {
    @ApiModelProperty("索引id")
    private String index_id;
    @ApiModelProperty("种类id")
    private String task_class_id;
    @ApiModelProperty("资料id")
    private String task_data_id;
    @ApiModelProperty("任务年份（未分配年份值为：0）")
    private String task_year;
    @ApiModelProperty("任务类型（年度任务，应急保障任务，日常任务）")
    private String task_type;
    @ApiModelProperty("任务名称")
    private String task_name;
    @ApiModelProperty("任务备注")
    private String task_remark;
    @ApiModelProperty("任务状态（1筹划，2下发，3任务进行，4任务质检，5制印，6已完成任务，7历史任务）")
    private String task_state;
    @ApiModelProperty("任务流程进度（手动填写，如：编辑，作业，校对）")
    private String task_rate;
    @ApiModelProperty("任务进度说明")
    private String task_rate_describe;
    @ApiModelProperty("是否分配部门（0：全部；1:已分配；2：未分配）")
    private Integer is_task_dept;
    /**
     * 图书名称
     */
    @ApiModelProperty("图书名称")
    private String task_class_tag;
    @ApiModelProperty("部门ID")
    private String dept_id;
    @ApiModelProperty("出厂时间")
    private String task_print_date;
    @ApiModelProperty("图号/书号")
    private String data_code;
    @ApiModelProperty("图名/书名")
    private String data_name;
    /**
     * 子分类id
     */
    @ApiModelProperty("子项所有ID")
    private List<String> child_id_list;
    @ApiModelProperty("任务验收时间")
    private String task_end_date;
    @ApiModelProperty("分配部门名称")
    private String dept_name;

    public String getIndex_id() {
        return index_id;
    }

    public void setIndex_id(String index_id) {
        this.index_id = index_id;
    }

    public String getTask_class_id() {
        return task_class_id;
    }

    public void setTask_class_id(String task_class_id) {
        this.task_class_id = task_class_id;
    }

    public String getTask_data_id() {
        return task_data_id;
    }

    public void setTask_data_id(String task_data_id) {
        this.task_data_id = task_data_id;
    }

    public String getTask_year() {
        return task_year;
    }

    public void setTask_year(String task_year) {
        this.task_year = task_year;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_remark() {
        return task_remark;
    }

    public void setTask_remark(String task_remark) {
        this.task_remark = task_remark;
    }

    public String getTask_state() {
        return task_state;
    }

    public void setTask_state(String task_state) {
        this.task_state = task_state;
    }

    public String getTask_rate() {
        return task_rate;
    }

    public void setTask_rate(String task_rate) {
        this.task_rate = task_rate;
    }

    public String getTask_rate_describe() {
        return task_rate_describe;
    }

    public void setTask_rate_describe(String task_rate_describe) {
        this.task_rate_describe = task_rate_describe;
    }

    public Integer getIs_task_dept() {
        return is_task_dept;
    }

    public void setIs_task_dept(Integer is_task_dept) {
        this.is_task_dept = is_task_dept;
    }

    public String getTask_class_tag() {
        return task_class_tag;
    }

    public void setTask_class_tag(String task_class_tag) {
        this.task_class_tag = task_class_tag;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getTask_print_date() {
        return task_print_date;
    }

    public void setTask_print_date(String task_print_date) {
        this.task_print_date = task_print_date;
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

    public List<String> getChild_id_list() {
        return child_id_list;
    }

    public void setChild_id_list(List<String> child_id_list) {
        this.child_id_list = child_id_list;
    }

    public String getTask_end_date() {
        return task_end_date;
    }

    public void setTask_end_date(String task_end_date) {
        this.task_end_date = task_end_date;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
