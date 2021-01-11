package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-11:29
 * @描述 应急保障任务模型
 */
@ApiModel("应急保障任务模型")
public class wm_task_others {
    @ApiModelProperty("其他任务ID")
    private String otask_id;
    @ApiModelProperty("任务索引ID")
    private String index_id;
    @ApiModelProperty("对应编号/记录编号")
    private String otask_code;
    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String otask_name;
    @ApiModelProperty("任务来源")
    private String otask_source;
    @ApiModelProperty("下达人员")
    private String otask_maker;
    @ApiModelProperty("承担单位")
    private String otask_unit;
    @ApiModelProperty("参与兵力")
    private String otask_people;
    @ApiModelProperty("下达时间")
    private String task_start_date;
    @ApiModelProperty("具体内容")
    private String otask_content;
    @ApiModelProperty("完成数量")
    private String otask_count;
    @ApiModelProperty("任务折算")
    private String otask_works;
    @ApiModelProperty("种类ID")
    private String class_id;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getOtask_id() {
        return otask_id;
    }

    public void setOtask_id(String otask_id) {
        this.otask_id = otask_id;
    }

    public String getIndex_id() {
        return index_id;
    }

    public void setIndex_id(String index_id) {
        this.index_id = index_id;
    }

    public String getOtask_code() {
        return otask_code;
    }

    public void setOtask_code(String otask_code) {
        this.otask_code = otask_code;
    }

    public String getOtask_name() {
        return otask_name;
    }

    public void setOtask_name(String otask_name) {
        this.otask_name = otask_name;
    }

    public String getOtask_source() {
        return otask_source;
    }

    public void setOtask_source(String otask_source) {
        this.otask_source = otask_source;
    }

    public String getOtask_maker() {
        return otask_maker;
    }

    public void setOtask_maker(String otask_maker) {
        this.otask_maker = otask_maker;
    }

    public String getOtask_unit() {
        return otask_unit;
    }

    public void setOtask_unit(String otask_unit) {
        this.otask_unit = otask_unit;
    }

    public String getOtask_people() {
        return otask_people;
    }

    public void setOtask_people(String otask_people) {
        this.otask_people = otask_people;
    }

    public String getTask_start_date() {
        return task_start_date;
    }

    public void setTask_start_date(String task_start_date) {
        this.task_start_date = task_start_date;
    }

    public String getOtask_content() {
        return otask_content;
    }

    public void setOtask_content(String otask_content) {
        this.otask_content = otask_content;
    }

    public String getOtask_count() {
        return otask_count;
    }

    public void setOtask_count(String otask_count) {
        this.otask_count = otask_count;
    }

    public String getOtask_works() {
        return otask_works;
    }

    public void setOtask_works(String otask_works) {
        this.otask_works = otask_works;
    }
}
