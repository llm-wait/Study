package com.startest.wm.model.score;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-15 15:05
 * @描述 综合测评成绩结果模型
 **/
@ApiModel("综合测评成绩结果模型对象")
public class ZhcpScoreModel {
    @ApiModelProperty("用户ID")
    private String user_id;//用户ID
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
    @ApiModelProperty("质量排名")
    private int zlpm;
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
    @ApiModelProperty("业务能力")
    private double ywnl;
    @ApiModelProperty("工作态度")
    private double gztd;
    @ApiModelProperty("能力态度总得分")
    private double nltdzf;
    @ApiModelProperty("能力态度得分排名")
    private int nltdpm;
    @ApiModelProperty("出勤率")
    private double cql;
    @ApiModelProperty("出勤率得分")
    private double cqldf;
    @ApiModelProperty("训练成绩")
    private double xlcj;
    @ApiModelProperty("训练成绩得分")
    private double xlcjdf;
    @ApiModelProperty("比武成绩")
    private double bwcj;
    @ApiModelProperty("比武成绩得分")
    private double bwcjdf;
    @ApiModelProperty("业务训练总得分")
    private double ywxlzdf;
    @ApiModelProperty("训练排名")
    private int ywxlpm;
    @ApiModelProperty("通报表扬得分")
    private double tbby;
    @ApiModelProperty("考评总分")
    private double kpzf;
    @ApiModelProperty("考评总排名")
    private int kpzpm;
    @ApiModelProperty("评定结果")
    private String pdjg;//评定结果

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

    public double getTbby() {
        return tbby;
    }

    public void setTbby(double tbby) {
        this.tbby = tbby;
    }

    public double getKpzf() {
        return kpzf;
    }

    public void setKpzf(double kpzf) {
        this.kpzf = kpzf;
    }

    public int getKpzpm() {
        return kpzpm;
    }

    public void setKpzpm(int kpzpm) {
        this.kpzpm = kpzpm;
    }

    public String getPdjg() {
        return pdjg;
    }

    public void setPdjg(String pdjg) {
        this.pdjg = pdjg;
    }
}
