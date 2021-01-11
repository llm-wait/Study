package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-09-15:21
 * @描述 质检索引数据模型
 */
@ApiModel("质检索引数据模型")
public class wm_check_index {
    @ApiModelProperty("质检索引ID")
    private String check_index_id;
    @ApiModelProperty("任务索引ID")
    private String task_index_id;
    @ApiModelProperty("部门ID")
    private String dept_id;
    @ApiModelProperty("部门名称")
    private String dept_name;
    @ApiModelProperty("图号/书号")
    private String chart_book_code;
    @ApiModelProperty("图名/书名")
    private String chart_book_name;
    @ApiModelProperty("交图时间/交书时间")
    private String start_check_date;
    @ApiModelProperty("海图/书表是否退回")
    private String chart_book_back;
    @ApiModelProperty("图例表是否退回（书表无）")
    private String chart_pdf_back;
    @ApiModelProperty("是否技术修改（书表无）")
    private String chart_edit;
    @ApiModelProperty("是否改成图（书表无）")
    private String chart_map_edit;
    @ApiModelProperty("主要负责人（书表无）")
    private String chart_manager;
    @ApiModelProperty("主要负责人ID")
    private String chart_manager_id;
    @ApiModelProperty("次要负责人（书表无）")
    private String chart_manager1;
    @ApiModelProperty("次要负责人ID")
    private String chart_manager1_id;

    @ApiModelProperty("海图类型/书表章节(不保存)")
    private String type_string;
    @ApiModelProperty("制图编辑(不保存)")
    private String chart_editor;
    @ApiModelProperty("作业员(不保存)")
    private String chart_maker;
    @ApiModelProperty("组长(不保存)")
    private String chart_leader;
    @ApiModelProperty("分配内容(海图：数字图，纸图，S57图)(书表：第一章)")
    private String distribution_type;
    @ApiModelProperty("产品类型（海图、书表、其他）")
    private String product_type;


    public String getStart_check_date() {
        return start_check_date;
    }

    public void setStart_check_date(String start_check_date) {
        this.start_check_date = start_check_date;
    }
    public String getCheck_index_id() {
        return check_index_id;
    }

    public void setCheck_index_id(String check_index_id) {
        this.check_index_id = check_index_id;
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

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getChart_book_code() {
        return chart_book_code;
    }

    public void setChart_book_code(String chart_book_code) {
        this.chart_book_code = chart_book_code;
    }

    public String getChart_book_name() {
        return chart_book_name;
    }

    public void setChart_book_name(String chart_book_name) {
        this.chart_book_name = chart_book_name;
    }


    public String getChart_book_back() {
        return chart_book_back;
    }

    public void setChart_book_back(String chart_book_back) {
        this.chart_book_back = chart_book_back;
    }

    public String getChart_pdf_back() {
        return chart_pdf_back;
    }

    public void setChart_pdf_back(String chart_pdf_back) {
        this.chart_pdf_back = chart_pdf_back;
    }

    public String getChart_edit() {
        return chart_edit;
    }

    public void setChart_edit(String chart_edit) {
        this.chart_edit = chart_edit;
    }

    public String getChart_map_edit() {
        return chart_map_edit;
    }

    public void setChart_map_edit(String chart_map_edit) {
        this.chart_map_edit = chart_map_edit;
    }

    public String getChart_manager() {
        return chart_manager;
    }

    public void setChart_manager(String chart_manager) {
        this.chart_manager = chart_manager;
    }

    public String getChart_manager1() {
        return chart_manager1;
    }

    public void setChart_manager1(String chart_manager1) {
        this.chart_manager1 = chart_manager1;
    }

    public String getType_string() {
        return type_string;
    }

    public void setType_string(String type_string) {
        this.type_string = type_string;
    }

    public String getChart_editor() {
        return chart_editor;
    }

    public void setChart_editor(String chart_editor) {
        this.chart_editor = chart_editor;
    }

    public String getChart_maker() {
        return chart_maker;
    }

    public void setChart_maker(String chart_maker) {
        this.chart_maker = chart_maker;
    }

    public String getChart_leader() {
        return chart_leader;
    }

    public void setChart_leader(String chart_leader) {
        this.chart_leader = chart_leader;
    }

    public String getDistribution_type() {
        return distribution_type;
    }

    public void setDistribution_type(String distribution_type) {
        this.distribution_type = distribution_type;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getChart_manager_id() {
        return chart_manager_id;
    }

    public void setChart_manager_id(String chart_manager_id) {
        this.chart_manager_id = chart_manager_id;
    }

    public String getChart_manager1_id() {
        return chart_manager1_id;
    }

    public void setChart_manager1_id(String chart_manager1_id) {
        this.chart_manager1_id = chart_manager1_id;
    }
}
