package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-01-16:45
 * @描述 书号/CIP申请单及条形码
 */
@ApiModel("书号/CIP申请单及条形码")
public class wm_task_bookinfo {
    @ApiModelProperty("")
    private String book_id;
    @ApiModelProperty("")
    private String task_index_id;
    @ApiModelProperty("")
    private String book_name;
    @ApiModelProperty("")
    private String book_code;
    @ApiModelProperty("")
    private String book_author;
    @ApiModelProperty("")
    private String responsible_editor;
    @ApiModelProperty("")
    private String unit_post;
    @ApiModelProperty("")
    private String post_title;
    @ApiModelProperty("")
    private Date first_examination_date;
    @ApiModelProperty("")
    private Date second_examination_date;
    @ApiModelProperty("")
    private Date accept_date;
    @ApiModelProperty("")
    private Date book_record_date;
    @ApiModelProperty("")
    private Date record_examination_date;
    @ApiModelProperty("")
    private Date end_examination_date;
    @ApiModelProperty("")
    private Date outer_date;
    @ApiModelProperty("")
    private Date outer_record_date;
    @ApiModelProperty("")
    private Date contract_date;
    @ApiModelProperty("")
    private String design_editor;
    @ApiModelProperty("")
    private String responsible_checker;
    @ApiModelProperty("")
    private Date contract_record_date;
    @ApiModelProperty("")
    private String print_unit;
    @ApiModelProperty("")
    private String publish_unit;
    @ApiModelProperty("")
    private String publish_range;
    @ApiModelProperty("")
    private String book_quality;
    @ApiModelProperty("")
    private String book_content;
    @ApiModelProperty("")
    private String first_check_content;
    @ApiModelProperty("")
    private String first_checker;
    @ApiModelProperty("")
    private String second_check_content;
    @ApiModelProperty("")
    private String second_checker;
    @ApiModelProperty("")
    private String third_check_content;
    @ApiModelProperty("")
    private String third_checker;
    @ApiModelProperty("")
    private String main_dept_content;
    @ApiModelProperty("")
    private String dept_manager;
    @ApiModelProperty("")
    private String publish_book_content;
    @ApiModelProperty("")
    private String publish_maker;
    @ApiModelProperty("")
    private Date accept_code_date;
    @ApiModelProperty("")
    private Date publish_code_date;
    @ApiModelProperty("")
    private String qr_code;
    @ApiModelProperty("")
    private String qr_image;
}
