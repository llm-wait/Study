package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-27 9:23
 * @描述 用户成绩信息表
 **/
@ApiModel("用户考评成绩操作模块API")
public class sys_user_score {
    @ApiModelProperty("唯一ID")
    private String id;
    @ApiModelProperty("考评年份")
    private Integer kpnf;
    @ApiModelProperty("用户ID")
    private String user_id;
    @ApiModelProperty("姓名")
    private String user_name;
    @ApiModelProperty("部门ID")
    private String dept_id;
    @ApiModelProperty("部门名称")
    private String dept_name;
    @ApiModelProperty("所在小组")
    private String szxz;
    @ApiModelProperty("岗位名称")
    private String station_name;
    @ApiModelProperty("是否组长")
    private String sfzz;
    @ApiModelProperty("组别")
    private String zb;
    @ApiModelProperty("人员类别")
    private String rylb;
    @ApiModelProperty("额定工天")
    private Double edgt;
    @ApiModelProperty("业务能力")
    private Double ywnl;
    @ApiModelProperty("工作态度")
    private Double gztd;
    @ApiModelProperty("出勤率")
    private Double cql;
    @ApiModelProperty("训练成绩")
    private Double xlcj;
    @ApiModelProperty("比武成绩")
    private Double bwcj;
    @ApiModelProperty("通报表扬")
    private Integer tbby;
    @ApiModelProperty("是否参与考评")
    private String sfcykp;
    @ApiModelProperty("是否记过")
    private String sfjg;
    @ApiModelProperty("犯错信息")
    private String errorinfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getKpnf() {
        return kpnf;
    }

    public void setKpnf(Integer kpnf) {
        this.kpnf = kpnf;
    }

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

    public String getSzxz() {
        return szxz;
    }

    public void setSzxz(String szxz) {
        this.szxz = szxz;
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

    public String getZb() {
        return zb;
    }

    public void setZb(String zb) {
        this.zb = zb;
    }

    public String getRylb() {
        return rylb;
    }

    public void setRylb(String rylb) {
        this.rylb = rylb;
    }

    public Double getEdgt() {
        return edgt;
    }

    public void setEdgt(Double edgt) {
        this.edgt = edgt;
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

    public Double getCql() {
        return cql;
    }

    public void setCql(Double cql) {
        this.cql = cql;
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

    public Integer getTbby() {
        return tbby;
    }

    public void setTbby(Integer tbby) {
        this.tbby = tbby;
    }

    public String getSfcykp() {
        return sfcykp;
    }

    public void setSfcykp(String sfcykp) {
        this.sfcykp = sfcykp;
    }

    public String getSfjg() {
        return sfjg;
    }

    public void setSfjg(String sfjg) {
        this.sfjg = sfjg;
    }

    public String getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo;
    }
}
