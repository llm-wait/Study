package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-25-14:32
 * @描述 标准书号申领单
 */
@ApiModel("标准书号申领单")
public class wm_form_book {
    @ApiModelProperty("ID")
    private String form_id;
    @ApiModelProperty("任务索引Id")
    private String task_index_id;
    @ApiModelProperty("军审号")
    private String book_jcode;
    @ApiModelProperty("选题号")
    private String book_code;
    @ApiModelProperty("书名")
    private String book_name;
    @ApiModelProperty("标准书号")
    private String book_standard_code;
    @ApiModelProperty("单位及职务名称")
    private String book_unit_post;
    @ApiModelProperty("责任编辑")
    private String book_edit;
    @ApiModelProperty("职务及职称")
    private String book_post_title;
    @ApiModelProperty("备注")
    private String remark;

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
    }

    public String getBook_jcode() {
        return book_jcode;
    }

    public void setBook_jcode(String book_jcode) {
        this.book_jcode = book_jcode;
    }

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String book_code) {
        this.book_code = book_code;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_standard_code() {
        return book_standard_code;
    }

    public void setBook_standard_code(String book_standard_code) {
        this.book_standard_code = book_standard_code;
    }

    public String getBook_unit_post() {
        return book_unit_post;
    }

    public void setBook_unit_post(String book_unit_post) {
        this.book_unit_post = book_unit_post;
    }

    public String getBook_edit() {
        return book_edit;
    }

    public void setBook_edit(String book_edit) {
        this.book_edit = book_edit;
    }

    public String getBook_post_title() {
        return book_post_title;
    }

    public void setBook_post_title(String book_post_title) {
        this.book_post_title = book_post_title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
