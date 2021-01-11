package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-12-10:24
 * @描述 书表通告数据模型
 */
@ApiModel("书表通告数据模型")
public class wm_book_notice {
    @ApiModelProperty("唯一ID")
    private String notice_id;
    @ApiModelProperty("任务索引ID")
    private String task_index_id;
    @ApiModelProperty("年份")
    private String notice_year;
    @ApiModelProperty("季度")
    private String notice_quarter;
    @ApiModelProperty("产品类型(航海通告，英文版航海通告)")
    private String notice_type;
    @ApiModelProperty("期数")
    private String notice_number;
    @ApiModelProperty("项数")
    private String notice_item;
    @ApiModelProperty("一类错误")
    private String first_error;
    @ApiModelProperty("错误总数")
    private String error_count;
    @ApiModelProperty("质量评定")
    private String notice_quality;

    public String getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
    }

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
    }

    public String getNotice_year() {
        return notice_year;
    }

    public void setNotice_year(String notice_year) {
        this.notice_year = notice_year;
    }

    public String getNotice_quarter() {
        return notice_quarter;
    }

    public void setNotice_quarter(String notice_quarter) {
        this.notice_quarter = notice_quarter;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public String getNotice_number() {
        return notice_number;
    }

    public void setNotice_number(String notice_number) {
        this.notice_number = notice_number;
    }

    public String getNotice_item() {
        return notice_item;
    }

    public void setNotice_item(String notice_item) {
        this.notice_item = notice_item;
    }

    public String getFirst_error() {
        return first_error;
    }

    public void setFirst_error(String first_error) {
        this.first_error = first_error;
    }

    public String getError_count() {
        return error_count;
    }

    public void setError_count(String error_count) {
        this.error_count = error_count;
    }

    public String getNotice_quality() {
        return notice_quality;
    }

    public void setNotice_quality(String notice_quality) {
        this.notice_quality = notice_quality;
    }
}
