package com.startest.wm.model.score;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-15 15:07
 * @描述 工天测评成绩结果模型
 **/
@ApiModel("工天测评成绩结果模型对象")
public class GtcpScoreModel {
    @ApiModelProperty("用户ID")
    private String user_id;
    @ApiModelProperty("姓名")
    private String user_name;
    @ApiModelProperty("岗位")
    private String station_name;
    @ApiModelProperty("组长")
    private String sfzz;
    @ApiModelProperty("工天")
    private double gt;
    @ApiModelProperty("工天得分")
    private double gtdf;
    @ApiModelProperty("工天比")
    private double gtb;
    @ApiModelProperty("工天比得分")
    private double gtbdf;
    @ApiModelProperty("工天总得分")
    private double gtzdf;
    @ApiModelProperty("工天得分排名")
    private int gtpm;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getSfzz() {
        return sfzz;
    }

    public void setSfzz(String sfzz) {
        this.sfzz = sfzz;
    }

    public double getGt() {
        return gt;
    }

    public void setGt(double gt) {
        this.gt = gt;
    }

    public double getGtdf() {
        return gtdf;
    }

    public void setGtdf(double gtdf) {
        this.gtdf = gtdf;
    }

    public double getGtb() {
        return gtb;
    }

    public void setGtb(double gtb) {
        this.gtb = gtb;
    }

    public double getGtbdf() {
        return gtbdf;
    }

    public void setGtbdf(double gtbdf) {
        this.gtbdf = gtbdf;
    }

    public double getGtzdf() {
        return gtzdf;
    }

    public void setGtzdf(double gtzdf) {
        this.gtzdf = gtzdf;
    }

    public int getGtpm() {
        return gtpm;
    }

    public void setGtpm(int gtpm) {
        this.gtpm = gtpm;
    }
}
