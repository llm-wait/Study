package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-22-16:41
 * @描述 书表产品质量评定表
 */
@ApiModel("书表产品质量评定模型")
public class wm_quality_book {
    @ApiModelProperty("唯一Id")
    private String book_id;
    @ApiModelProperty("任务ID")
    private String task_index_id;
    @ApiModelProperty("年份")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date task_year;
    @ApiModelProperty("产品分类")
    private String book_class;
    @ApiModelProperty("产品名称")
    private String book_name;
    @ApiModelProperty("产品类型")
    private String book_type;
    @ApiModelProperty("产品编号")
    private String book_code;
    @ApiModelProperty("字数")
    private String book_words;
    @ApiModelProperty("错漏值/工天")
    private String book_error_workdays;
    @ApiModelProperty("错漏质量")
    private String book_quality;
    @ApiModelProperty("编译质量")
    private String book_edit_quality;
    @ApiModelProperty("综合质量评定")
    private String book_evaluation;
    @ApiModelProperty("季度")
    private String quarter_number;

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
    }

    public Date getTask_year() {
        return task_year;
    }

    public void setTask_year(Date task_year) {
        this.task_year = task_year;
    }

    public String getBook_class() {
        return book_class;
    }

    public void setBook_class(String book_class) {
        this.book_class = book_class;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String book_code) {
        this.book_code = book_code;
    }

    public String getBook_words() {
        return book_words;
    }

    public void setBook_words(String book_words) {
        this.book_words = book_words;
    }

    public String getBook_error_workdays() {
        return book_error_workdays;
    }

    public void setBook_error_workdays(String book_error_workdays) {
        this.book_error_workdays = book_error_workdays;
    }

    public String getBook_quality() {
        return book_quality;
    }

    public void setBook_quality(String book_quality) {
        this.book_quality = book_quality;
    }

    public String getBook_edit_quality() {
        return book_edit_quality;
    }

    public void setBook_edit_quality(String book_edit_quality) {
        this.book_edit_quality = book_edit_quality;
    }

    public String getBook_evaluation() {
        return book_evaluation;
    }

    public void setBook_evaluation(String book_evaluation) {
        this.book_evaluation = book_evaluation;
    }

    public String getQuarter_number() {
        return quarter_number;
    }

    public void setQuarter_number(String quarter_number) {
        this.quarter_number = quarter_number;
    }
}
