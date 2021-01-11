package com.startest.wm.model.score;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-15 15:08
 * @描述 能力态度测评成绩结果模型
 **/
@ApiModel("能力态度测评成绩结果模型对象")
public class NltdcpScoreModel {
    @ApiModelProperty("用户ID")
    private String user_id;
    @ApiModelProperty("姓名")
    private String user_name;
    @ApiModelProperty("岗位")
    private String station_name;
    @ApiModelProperty("组长")
    private String sfzz;
    @ApiModelProperty("业务能力")
    private double ywnl;
    @ApiModelProperty("工作态度")
    private double gztd;
    @ApiModelProperty("能力态度总得分")
    private double nltdzf;
    @ApiModelProperty("能力态度得分排名")
    private int nltdpm;

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

    public double getYwnl() {
        return ywnl;
    }

    public void setYwnl(double ywnl) {
        this.ywnl = ywnl;
    }

    public double getGztd() {
        return gztd;
    }

    public void setGztd(double gztd) {
        this.gztd = gztd;
    }

    public double getNltdzf() {
        return nltdzf;
    }

    public void setNltdzf(double nltdzf) {
        this.nltdzf = nltdzf;
    }

    public int getNltdpm() {
        return nltdpm;
    }

    public void setNltdpm(int nltdpm) {
        this.nltdpm = nltdpm;
    }
}
