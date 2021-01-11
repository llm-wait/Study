package com.startest.wm.model;

import com.startest.wm.utils.MyDateUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-22-19:35
 * @描述 任务核心数据模型
 */
public class TaskIndexModel {
    @ApiModelProperty("索引id")
    private String index_id;
    @ApiModelProperty("种类id")
    private String task_class_id;
    @ApiModelProperty("资料id")
    private String task_data_id;
    @ApiModelProperty("部门ID")
    private String dept_id;
    @ApiModelProperty("任务信息ID")
    private String task_info_id;
    @ApiModelProperty("任务年份（未分配年份值为：0）")
    private String task_year;
    @ApiModelProperty("任务类型（制图任务，书表任务，应急保障任务，日常任务）")
    private String task_type;
    @ApiModelProperty("任务名称")
    private String task_name;
    @ApiModelProperty("图号")
    private String data_code;
    @ApiModelProperty("任务备注")
    private String task_remark;
    @ApiModelProperty("图书名称")
    private String task_class_tag;
    @ApiModelProperty("出厂时间")
    @DateTimeFormat(pattern= MyDateUtils.DATE_PATTERN_SECOND)
    private String task_print_date;
    @ApiModelProperty("任务内容（添印、改版）")
    private String task_type_content;
    @ApiModelProperty("任务下达时间")
    @DateTimeFormat(pattern=MyDateUtils.DATE_PATTERN_SECOND)
    private String task_start_date;
    @ApiModelProperty("任务状态（1筹划，2下发，3任务进行，4任务质检，5制印，6已完成任务，7历史任务）")
    private String task_state;
    @ApiModelProperty("下达人")
    private String otask_maker;
    @ApiModelProperty("任务状态列表")
    private String task_state_list;


    public String getData_code() {
        return data_code;
    }

    public void setData_code(String data_code) {
        this.data_code = data_code;
    }

    public String getOtask_maker() {
        return otask_maker;
    }

    public void setOtask_maker(String otask_maker) {
        this.otask_maker = otask_maker;
    }

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

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getTask_info_id() {
        return task_info_id;
    }

    public void setTask_info_id(String task_info_id) {
        this.task_info_id = task_info_id;
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

    public String getTask_class_tag() {
        return task_class_tag;
    }

    public void setTask_class_tag(String task_class_tag) {
        this.task_class_tag = task_class_tag;
    }

    public String getTask_print_date() {
        return task_print_date;
    }

    public void setTask_print_date(String task_print_date) {
        this.task_print_date = task_print_date;
    }

    public String getTask_type_content() {
        return task_type_content;
    }

    public void setTask_type_content(String task_type_content) {
        this.task_type_content = task_type_content;
    }

    public String getTask_start_date() {
        return task_start_date;
    }

    public void setTask_start_date(String task_start_date) {
        this.task_start_date = task_start_date;
    }

    public String getTask_state() {
        return task_state;
    }

    public void setTask_state(String task_state) {
        this.task_state = task_state;
    }

    public String getTask_state_list() {
        return task_state_list;
    }

    public void setTask_state_list(String task_state_list) {
        this.task_state_list = task_state_list;
    }
}
