package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:29
 * @描述 机关核准工天信息表实体
 **/
@ApiModel("机关核准工天")
public class sys_jghzgt {
    @ApiModelProperty("唯一ID")
    private String id;//唯一ID
    @ApiModelProperty("部门ID")
    private String dept_id;//部门ID
    @ApiModelProperty("年份")
    private Integer year;//年份
    @ApiModelProperty("机关核准工天")
    private Double jghzgt;//机关核准工天
    @ApiModelProperty("单位")
    private String dept_name;//单位
    @ApiModelProperty("单位总人数")
    private Integer dept_zrs;//单位总人数
    @ApiModelProperty("单位考评人数")
    private Integer dept_kprs;//单位考评人数
    @ApiModelProperty("单位实际总共天")
    private double dept_sjzgt;//单位实际总共天
    @ApiModelProperty("单位考评总工天")
    private double dept_kpzgt;//单位考评总工天
    @ApiModelProperty("单位人均工天")
    private double dept_rjgt;//单位人均工天
    @ApiModelProperty("单位考评人均工天")
    private double dept_rjkpgt;//单位考评人均工天
    @ApiModelProperty("单位优秀总工天")
    private double dept_yxzgt;//单位优秀总工天
    @ApiModelProperty("单位优秀考评总共天")
    private double dept_yxkpzgt;//单位优秀考评总共天
    @ApiModelProperty("单位平均优秀工天")
    private double dept_pjyxgt;//单位平均优秀工天
    @ApiModelProperty("单位平均优秀率")
    private double dept_pjyxl;//单位平均优秀率
    @ApiModelProperty("全社平均工天")
    private double qspjgt;//全社平均工天
    @ApiModelProperty("全社平均优秀率")
    private double qspjyxl;//全社平均优秀率

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getJghzgt() {
        return jghzgt;
    }

    public void setJghzgt(Double jghzgt) {
        this.jghzgt = jghzgt;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public Integer getDept_zrs() {
        return dept_zrs;
    }

    public void setDept_zrs(Integer dept_zrs) {
        this.dept_zrs = dept_zrs;
    }

    public Integer getDept_kprs() {
        return dept_kprs;
    }

    public void setDept_kprs(Integer dept_kprs) {
        this.dept_kprs = dept_kprs;
    }

    public double getDept_sjzgt() {
        return dept_sjzgt;
    }

    public void setDept_sjzgt(double dept_sjzgt) {
        this.dept_sjzgt = dept_sjzgt;
    }

    public double getDept_kpzgt() {
        return dept_kpzgt;
    }

    public void setDept_kpzgt(double dept_kpzgt) {
        this.dept_kpzgt = dept_kpzgt;
    }

    public double getDept_rjgt() {
        return dept_rjgt;
    }

    public void setDept_rjgt(double dept_rjgt) {
        this.dept_rjgt = dept_rjgt;
    }

    public double getDept_rjkpgt() {
        return dept_rjkpgt;
    }

    public void setDept_rjkpgt(double dept_rjkpgt) {
        this.dept_rjkpgt = dept_rjkpgt;
    }

    public double getDept_yxzgt() {
        return dept_yxzgt;
    }

    public void setDept_yxzgt(double dept_yxzgt) {
        this.dept_yxzgt = dept_yxzgt;
    }

    public double getDept_yxkpzgt() {
        return dept_yxkpzgt;
    }

    public void setDept_yxkpzgt(double dept_yxkpzgt) {
        this.dept_yxkpzgt = dept_yxkpzgt;
    }

    public double getDept_pjyxgt() {
        return dept_pjyxgt;
    }

    public void setDept_pjyxgt(double dept_pjyxgt) {
        this.dept_pjyxgt = dept_pjyxgt;
    }

    public double getDept_pjyxl() {
        return dept_pjyxl;
    }

    public void setDept_pjyxl(double dept_pjyxl) {
        this.dept_pjyxl = dept_pjyxl;
    }

    public double getQspjgt() {
        return qspjgt;
    }

    public void setQspjgt(double qspjgt) {
        this.qspjgt = qspjgt;
    }

    public double getQspjyxl() {
        return qspjyxl;
    }

    public void setQspjyxl(double qspjyxl) {
        this.qspjyxl = qspjyxl;
    }
}
