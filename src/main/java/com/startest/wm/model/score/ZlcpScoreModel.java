package com.startest.wm.model.score;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-15 15:06
 * @描述 质量测评成绩结果模型
 **/
@ApiModel("质量测评成绩结果模型对象")
public class ZlcpScoreModel {
    @ApiModelProperty("用户ID")
    private String user_id;
    @ApiModelProperty("姓名")
    private String user_name;
    @ApiModelProperty("岗位")
    private String station_name;
    @ApiModelProperty("组长")
    private String sfzz;
    @ApiModelProperty("合格率")
    private double hgl;
    @ApiModelProperty("合格率得分")
    private double hgldf;
    @ApiModelProperty("优秀率")
    private double yxl;
    @ApiModelProperty("优秀率得分")
    private double yxldf;
    @ApiModelProperty("优秀工天")
    private String yxgt;
    @ApiModelProperty("优秀工天比")
    private double yxgtb;
    @ApiModelProperty("优秀工天比得分")
    private double yxgtbdf;
    @ApiModelProperty("改成图扣分")
    private double gctkf;
    @ApiModelProperty("技术修改扣分")
    private double jsxgkf;
    @ApiModelProperty("质量总得分")
    private double zlzdf;
    @ApiModelProperty("质量得分排名")
    private int zlpm;

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

    public double getHgl() {
        return hgl;
    }

    public void setHgl(double hgl) {
        this.hgl = hgl;
    }

    public double getHgldf() {
        return hgldf;
    }

    public void setHgldf(double hgldf) {
        this.hgldf = hgldf;
    }

    public double getYxl() {
        return yxl;
    }

    public void setYxl(double yxl) {
        this.yxl = yxl;
    }

    public double getYxldf() {
        return yxldf;
    }

    public void setYxldf(double yxldf) {
        this.yxldf = yxldf;
    }

    public String getYxgt() {
        return yxgt;
    }

    public void setYxgt(String yxgt) {
        this.yxgt = yxgt;
    }

    public double getYxgtb() {
        return yxgtb;
    }

    public void setYxgtb(double yxgtb) {
        this.yxgtb = yxgtb;
    }

    public double getYxgtbdf() {
        return yxgtbdf;
    }

    public void setYxgtbdf(double yxgtbdf) {
        this.yxgtbdf = yxgtbdf;
    }

    public double getGctkf() {
        return gctkf;
    }

    public void setGctkf(double gctkf) {
        this.gctkf = gctkf;
    }

    public double getJsxgkf() {
        return jsxgkf;
    }

    public void setJsxgkf(double jsxgkf) {
        this.jsxgkf = jsxgkf;
    }

    public double getZlzdf() {
        return zlzdf;
    }

    public void setZlzdf(double zlzdf) {
        this.zlzdf = zlzdf;
    }

    public int getZlpm() {
        return zlpm;
    }

    public void setZlpm(int zlpm) {
        this.zlpm = zlpm;
    }
}
