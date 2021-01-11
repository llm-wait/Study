package com.startest.wm.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-14-14:23
 * @描述 书表任务数据模型
 */
public class TaskBookModel {

    @ApiModelProperty("唯一ID")
    private String port_id;//唯一ID
    @ApiModelProperty("港口编号")
    private String port_num;//港口编号
    @ApiModelProperty("港口中文名称")
    private String port_cn_name;//港口中文名称
    @ApiModelProperty("港口英文名称")
    private String port_en_name;//港口英文名称
    @ApiModelProperty("所属国家")
    private String port_country;//所属国家
    @ApiModelProperty("所属海区")
    private String port_sea;//所属海区
    @ApiModelProperty("港口资料来源")
    private String port_data_source;//港口资料来源
    @ApiModelProperty("版时")
    private String port_data_bs;//版时
    @ApiModelProperty("编译时间")
    private String port_data_bysj;//编译时间
    @ApiModelProperty("经度")
    private String port_lon;//经度
    @ApiModelProperty("纬度")
    private String port_lat;//纬度
    @ApiModelProperty("港口资料状态")
    private String port_state;//港口资料状态
    @ApiModelProperty("港口资料类型")
    private String port_type;
    @ApiModelProperty("备注")
    private String remark;//备注
    @ApiModelProperty("几何")
    private String shape;//几何
    @ApiModelProperty("版次")
    private String bc;
    @ApiModelProperty("任务索引ID")
    private String index_id;
    @ApiModelProperty("任务分配年份")
    private String task_year;
    @ApiModelProperty("任务状态")
    private String task_state;
    @ApiModelProperty("任务名称")
    private String task_name;

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getPort_id() {
        return port_id;
    }

    public void setPort_id(String port_id) {
        this.port_id = port_id;
    }

    public String getPort_num() {
        return port_num;
    }

    public void setPort_num(String port_num) {
        this.port_num = port_num;
    }

    public String getPort_cn_name() {
        return port_cn_name;
    }

    public void setPort_cn_name(String port_cn_name) {
        this.port_cn_name = port_cn_name;
    }

    public String getPort_en_name() {
        return port_en_name;
    }

    public void setPort_en_name(String port_en_name) {
        this.port_en_name = port_en_name;
    }

    public String getPort_country() {
        return port_country;
    }

    public void setPort_country(String port_country) {
        this.port_country = port_country;
    }

    public String getPort_sea() {
        return port_sea;
    }

    public void setPort_sea(String port_sea) {
        this.port_sea = port_sea;
    }

    public String getPort_data_source() {
        return port_data_source;
    }

    public void setPort_data_source(String port_data_source) {
        this.port_data_source = port_data_source;
    }

    public String getPort_data_bs() {
        return port_data_bs;
    }

    public void setPort_data_bs(String port_data_bs) {
        this.port_data_bs = port_data_bs;
    }

    public String getPort_data_bysj() {
        return port_data_bysj;
    }

    public void setPort_data_bysj(String port_data_bysj) {
        this.port_data_bysj = port_data_bysj;
    }

    public String getPort_lon() {
        return port_lon;
    }

    public void setPort_lon(String port_lon) {
        this.port_lon = port_lon;
    }

    public String getPort_lat() {
        return port_lat;
    }

    public void setPort_lat(String port_lat) {
        this.port_lat = port_lat;
    }

    public String getPort_state() {
        return port_state;
    }

    public void setPort_state(String port_state) {
        this.port_state = port_state;
    }

    public String getPort_type() {
        return port_type;
    }

    public void setPort_type(String port_type) {
        this.port_type = port_type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getIndex_id() {
        return index_id;
    }

    public void setIndex_id(String index_id) {
        this.index_id = index_id;
    }

    public String getTask_year() {
        return task_year;
    }

    public void setTask_year(String task_year) {
        this.task_year = task_year;
    }

    public String getTask_state() {
        return task_state;
    }

    public void setTask_state(String task_state) {
        this.task_state = task_state;
    }
}
