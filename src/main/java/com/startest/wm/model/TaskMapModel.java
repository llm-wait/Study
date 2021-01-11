package com.startest.wm.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-14-14:20
 * @描述 任务海图资料模型
 */
public class TaskMapModel {
    @ApiModelProperty("唯一ID")
    private String map_id;//唯一ID
    @ApiModelProperty("海图图号")
    private String map_code;//海图图号
    @ApiModelProperty("海图中文名称")
    private String map_cn_name;//海图中文名称
    @ApiModelProperty("海图英文名称")
    private String map_en_name;//海图英文名称
    @ApiModelProperty("海图区域")
    private String map_area;//海图区域
    @ApiModelProperty("海图图积")
    private String map_amass;//海图图积
    @ApiModelProperty("海图比例尺")
    private int map_scale;//海图比例尺
    @ApiModelProperty("南图廓纬度")
    private String south_lat;//南图廓纬度
    @ApiModelProperty("北图廓纬度")
    private String north_lat;//北图廓纬度
    @ApiModelProperty("西经")
    private String west_lon;//西图廓经度
    @ApiModelProperty("东经")
    private  String east_lon;//东图廓经度
    @ApiModelProperty("出版单位")
    private  String publish_unit;//出版单位
    @ApiModelProperty("出版日期")
    private  String publish_date;//出版日期
    @ApiModelProperty("海图类型")
    private  String map_type;//海图类型
    @ApiModelProperty("海图状态")
    private  String map_state;//海图状态
    @ApiModelProperty("备注")
    private  String remark;//备注
    @ApiModelProperty("几何")
    private  String shape;//几何
    @ApiModelProperty("库存情况")
    private String kcqk;//库存情况
    @ApiModelProperty("通告改正数")
    private String tggzs;//通告改正数
    @ApiModelProperty("新资料面积占比")
    private String xzlmjzb;//新资料面积占比
    @ApiModelProperty("是否改版")
    private String sfgb;//是否改版
    @ApiModelProperty("版次")
    private String bc;//是否改版
    @ApiModelProperty("任务索引ID")
    private String index_id;
    @ApiModelProperty("任务分配年份")
    private String task_year;
    @ApiModelProperty("任务状态")
    private String task_state;

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public String getMap_code() {
        return map_code;
    }

    public void setMap_code(String map_code) {
        this.map_code = map_code;
    }

    public String getMap_cn_name() {
        return map_cn_name;
    }

    public void setMap_cn_name(String map_cn_name) {
        this.map_cn_name = map_cn_name;
    }

    public String getMap_en_name() {
        return map_en_name;
    }

    public void setMap_en_name(String map_en_name) {
        this.map_en_name = map_en_name;
    }

    public String getMap_area() {
        return map_area;
    }

    public void setMap_area(String map_area) {
        this.map_area = map_area;
    }

    public String getMap_amass() {
        return map_amass;
    }

    public void setMap_amass(String map_amass) {
        this.map_amass = map_amass;
    }

    public int getMap_scale() {
        return map_scale;
    }

    public void setMap_scale(int map_scale) {
        this.map_scale = map_scale;
    }

    public String getSouth_lat() {
        return south_lat;
    }

    public void setSouth_lat(String south_lat) {
        this.south_lat = south_lat;
    }

    public String getNorth_lat() {
        return north_lat;
    }

    public void setNorth_lat(String north_lat) {
        this.north_lat = north_lat;
    }

    public String getWest_lon() {
        return west_lon;
    }

    public void setWest_lon(String west_lon) {
        this.west_lon = west_lon;
    }

    public String getEast_lon() {
        return east_lon;
    }

    public void setEast_lon(String east_lon) {
        this.east_lon = east_lon;
    }

    public String getPublish_unit() {
        return publish_unit;
    }

    public void setPublish_unit(String publish_unit) {
        this.publish_unit = publish_unit;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getMap_type() {
        return map_type;
    }

    public void setMap_type(String map_type) {
        this.map_type = map_type;
    }

    public String getMap_state() {
        return map_state;
    }

    public void setMap_state(String map_state) {
        this.map_state = map_state;
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

    public String getKcqk() {
        return kcqk;
    }

    public void setKcqk(String kcqk) {
        this.kcqk = kcqk;
    }

    public String getTggzs() {
        return tggzs;
    }

    public void setTggzs(String tggzs) {
        this.tggzs = tggzs;
    }

    public String getXzlmjzb() {
        return xzlmjzb;
    }

    public void setXzlmjzb(String xzlmjzb) {
        this.xzlmjzb = xzlmjzb;
    }

    public String getSfgb() {
        return sfgb;
    }

    public void setSfgb(String sfgb) {
        this.sfgb = sfgb;
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
