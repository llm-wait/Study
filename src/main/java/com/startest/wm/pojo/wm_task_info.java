package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-01-15:39
 * @描述 任务信息表（任务表单）
 */
@ApiModel("任务单")
public class wm_task_info {
    @ApiModelProperty("任务id")
    private String task_info_id;
    @ApiModelProperty("任务索引id")
    private String task_index_id;
    @ApiModelProperty("任务编号（年份+4位序号）")
    private String task_code;
    @ApiModelProperty("下达时间")
    private String task_start_date;
    @ApiModelProperty("完成期限")
    private String task_end_date;
    @ApiModelProperty("任务级别（正常、快速、紧急）")
    private String task_level;
    @ApiModelProperty("任务类型（新增、改版、添印、科研）")
    private String task_type_content;
    @ApiModelProperty("任务量")
    private String task_days;
    @ApiModelProperty("任务名称")
    private String task_name;
    @ApiModelProperty("工作内容及要求")
    private String task_content;
    @ApiModelProperty("备注")
    private String task_remark;
    @ApiModelProperty("任务种类ID")
    private String task_class_id;

    /**时间类型判断空
     * @param task_start_date 开始时间
     */
    public void setTask_start_date(String task_start_date) {
        if (task_start_date == null || task_start_date.isEmpty()) {
            this.task_start_date = null;
            return;
        }
        this.task_start_date = task_start_date;
    }

    public void setTask_end_date(String task_end_date) {
        if (task_end_date == null || task_end_date.isEmpty()) {
            this.task_end_date = null;
            return;
        }
        this.task_end_date = task_end_date;
    }


    public String getTask_info_id() {
        return task_info_id;
    }

    public void setTask_info_id(String task_info_id) {
        this.task_info_id = task_info_id;
    }

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
    }

    public String getTask_code() {
        return task_code;
    }

    public void setTask_code(String task_code) {
        this.task_code = task_code;
    }

    public String getTask_start_date() {
        return task_start_date;
    }


    public String getTask_end_date() {
        return task_end_date;
    }


    public String getTask_level() {
        return task_level;
    }

    public void setTask_level(String task_level) {
        this.task_level = task_level;
    }

    public String getTask_type_content() {
        return task_type_content;
    }

    public void setTask_type_content(String task_type_content) {
        this.task_type_content = task_type_content;
    }

    public String getTask_days() {
        return task_days;
    }

    public void setTask_days(String task_days) {
        this.task_days = task_days;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_content() {
        return task_content;
    }

    public void setTask_content(String task_content) {
        this.task_content = task_content;
    }

    public String getTask_remark() {
        return task_remark;
    }

    public void setTask_remark(String task_remark) {
        this.task_remark = task_remark;
    }

    public String getTask_class_id() {
        return task_class_id;
    }

    public void setTask_class_id(String task_class_id) {
        this.task_class_id = task_class_id;
    }
}
