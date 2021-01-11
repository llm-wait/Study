package com.startest.wm.model.score;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-15 15:09
 * @描述 业务训练测评成绩结果模型
 **/
@ApiModel("业务训练测评成绩结果模型对象")
public class YwxlcpScoreModel {
    @ApiModelProperty("用户ID")
    private String user_id;
    @ApiModelProperty("姓名")
    private String user_name;
    @ApiModelProperty("岗位")
    private String station_name;
    @ApiModelProperty("组长")
    private String sfzz;
    @ApiModelProperty("出勤率")
    private double cql;
    @ApiModelProperty("出勤率得分")
    private double cqldf;
    @ApiModelProperty("训练成绩")
    private double xlcj;
    @ApiModelProperty("训练得分")
    private double xlcjdf;
    @ApiModelProperty("比武成绩")
    private double bwcj;
    @ApiModelProperty("比武得分")
    private double bwcjdf;
    @ApiModelProperty("业务训练总得分")
    private double ywxlzdf;
    @ApiModelProperty("训练排名")
    private int ywxlpm;

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

    public double getCql() {
        return cql;
    }

    public void setCql(double cql) {
        this.cql = cql;
    }

    public double getCqldf() {
        return cqldf;
    }

    public void setCqldf(double cqldf) {
        this.cqldf = cqldf;
    }

    public double getXlcj() {
        return xlcj;
    }

    public void setXlcj(double xlcj) {
        this.xlcj = xlcj;
    }

    public double getXlcjdf() {
        return xlcjdf;
    }

    public void setXlcjdf(double xlcjdf) {
        this.xlcjdf = xlcjdf;
    }

    public double getBwcj() {
        return bwcj;
    }

    public void setBwcj(double bwcj) {
        this.bwcj = bwcj;
    }

    public double getBwcjdf() {
        return bwcjdf;
    }

    public void setBwcjdf(double bwcjdf) {
        this.bwcjdf = bwcjdf;
    }

    public double getYwxlzdf() {
        return ywxlzdf;
    }

    public void setYwxlzdf(double ywxlzdf) {
        this.ywxlzdf = ywxlzdf;
    }

    public int getYwxlpm() {
        return ywxlpm;
    }

    public void setYwxlpm(int ywxlpm) {
        this.ywxlpm = ywxlpm;
    }
}
