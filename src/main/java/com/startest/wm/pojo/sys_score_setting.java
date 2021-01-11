package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:16
 * @描述 考评成绩计算参数配置信息表数据实体
 **/
@ApiModel("考评成绩计算参数配置")
public class sys_score_setting {
    @ApiModelProperty("唯一ID")
    private String id;//唯一ID
    @ApiModelProperty("合格率")
    private Double hgl;//合格率
    @ApiModelProperty("优秀率")
    private Double yxl;//优秀率
    @ApiModelProperty("优秀工天比")
    private Double yxgtb;//优秀工天比
    @ApiModelProperty("改成图（主）")
    private Double gctz;//改成图（主）
    @ApiModelProperty("改成图（次）")
    private Double gctc;//改成图（次）
    @ApiModelProperty("技术修改（主）")
    private Double jsxgz;//技术修改（主）
    @ApiModelProperty("技术修改（次）")
    private Double jsxgc;//技术修改（次）
    @ApiModelProperty("业务工天")
    private Double ywgt;//业务工天
    @ApiModelProperty("工天比")
    private Double gtb;//工天比
    @ApiModelProperty("业务能力")
    private Double ywnl;//业务能力
    @ApiModelProperty("工作态度")
    private Double gztd;//工作态度
    @ApiModelProperty("参训出勤率")
    private Double cxcql;//参训出勤率
    @ApiModelProperty("训练成绩")
    private Double xlcj;//训练成绩
    @ApiModelProperty("比武成绩")
    private Double bwcj;//比武成绩
    @ApiModelProperty("年份")
    private Integer year;//年份

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getHgl() {
        return hgl;
    }

    public void setHgl(Double hgl) {
        this.hgl = hgl;
    }

    public Double getYxl() {
        return yxl;
    }

    public void setYxl(Double yxl) {
        this.yxl = yxl;
    }

    public Double getYxgtb() {
        return yxgtb;
    }

    public void setYxgtb(Double yxgtb) {
        this.yxgtb = yxgtb;
    }

    public Double getGctz() {
        return gctz;
    }

    public void setGctz(Double gctz) {
        this.gctz = gctz;
    }

    public Double getGctc() {
        return gctc;
    }

    public void setGctc(Double gctc) {
        this.gctc = gctc;
    }

    public Double getJsxgz() {
        return jsxgz;
    }

    public void setJsxgz(Double jsxgz) {
        this.jsxgz = jsxgz;
    }

    public Double getJsxgc() {
        return jsxgc;
    }

    public void setJsxgc(Double jsxgc) {
        this.jsxgc = jsxgc;
    }

    public Double getYwgt() {
        return ywgt;
    }

    public void setYwgt(Double ywgt) {
        this.ywgt = ywgt;
    }

    public Double getGtb() {
        return gtb;
    }

    public void setGtb(Double gtb) {
        this.gtb = gtb;
    }

    public Double getYwnl() {
        return ywnl;
    }

    public void setYwnl(Double ywnl) {
        this.ywnl = ywnl;
    }

    public Double getGztd() {
        return gztd;
    }

    public void setGztd(Double gztd) {
        this.gztd = gztd;
    }

    public Double getCxcql() {
        return cxcql;
    }

    public void setCxcql(Double cxcql) {
        this.cxcql = cxcql;
    }

    public Double getXlcj() {
        return xlcj;
    }

    public void setXlcj(Double xlcj) {
        this.xlcj = xlcj;
    }

    public Double getBwcj() {
        return bwcj;
    }

    public void setBwcj(Double bwcj) {
        this.bwcj = bwcj;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
