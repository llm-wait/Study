package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-27 9:23
 * @描述 用户成绩信息最终表
 **/
@ApiModel("用户最终成绩对象")
public class sys_user_finalscore {
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
    @ApiModelProperty("岗位名称")
    private String station_name;
    @ApiModelProperty("是否组长")
    private String sfzz;
    @ApiModelProperty("组别")
    private String zb;
    @ApiModelProperty("人员类别")
    private String rylb;
    @ApiModelProperty("合格率")
    private Double hgl;
    @ApiModelProperty("合格率得分")
    private Double hgldf;
    @ApiModelProperty("优秀率")
    private Double yxl;
    @ApiModelProperty("优秀率得分")
    private Double yxldf;
    @ApiModelProperty("优秀工天")
    private Double yxgt;
    @ApiModelProperty("优秀工天比")
    private Double yxgtb;
    @ApiModelProperty("优秀工天比得分")
    private Double yxgtbdf;
    @ApiModelProperty("改成图数量主")
    private Double gctzsl;
    @ApiModelProperty("改成图数量次")
    private Double gctcsl;
    @ApiModelProperty("改成图数量")
    private Double gctsl;
    @ApiModelProperty("改成图扣分")
    private Double gctkf;
    @ApiModelProperty("技术修改数量主")
    private Double jsxgzsl;
    @ApiModelProperty("技术修改数量次")
    private Double jsxgcsl;
    @ApiModelProperty("技术修改数量")
    private Double jsxgsl;
    @ApiModelProperty("技术修改扣分")
    private Double jsxgkf;
    @ApiModelProperty("质量总得分")
    private Double zlzdf;
    @ApiModelProperty("质量排名")
    private Integer zlpm;
    @ApiModelProperty("额定工天")
    private Double edgt;
    @ApiModelProperty("工天")
    private Double gt;
    @ApiModelProperty("工天得分")
    private Double gtdf;
    @ApiModelProperty("工天比")
    private Double gtb;
    @ApiModelProperty("工天比得分")
    private Double gtbdf;
    @ApiModelProperty("工天总得分")
    private Double gtzdf;
    @ApiModelProperty("工天得分排名")
    private Integer gtpm;
    @ApiModelProperty("业务能力")
    private Double ywnl;
    @ApiModelProperty("工作态度")
    private Double gztd;
    @ApiModelProperty("能力态度得分")
    private Double nltdzf;
    @ApiModelProperty("能力态度得分排名")
    private Integer nltdpm;
    @ApiModelProperty("出勤率")
    private Double cql;
    @ApiModelProperty("出勤率得分")
    private Double cqldf;
    @ApiModelProperty("训练成绩")
    private Double xlcj;
    @ApiModelProperty("训练得分")
    private Double xlcjdf;
    @ApiModelProperty("比武成绩")
    private Double bwcj;
    @ApiModelProperty("比武得分")
    private Double bwcjdf;
    @ApiModelProperty("业务训练总得分")
    private Double ywxlzdf;
    @ApiModelProperty("业务排名")
    private Integer ywxlpm;
    @ApiModelProperty("通报表扬")
    private Double tbby;//（0.5分每次,最终表记录分值）
    @ApiModelProperty("考评总分")
    private Double kpzf;
    @ApiModelProperty("考评总排名")
    private Integer kpzpm;
    @ApiModelProperty("评定结果")
    private String pdjg;
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

    public Double getHgl() {
        return hgl;
    }

    public void setHgl(Double hgl) {
        this.hgl = hgl;
    }

    public Double getHgldf() {
        return hgldf;
    }

    public void setHgldf(Double hgldf) {
        this.hgldf = hgldf;
    }

    public Double getYxl() {
        return yxl;
    }

    public void setYxl(Double yxl) {
        this.yxl = yxl;
    }

    public Double getYxldf() {
        return yxldf;
    }

    public void setYxldf(Double yxldf) {
        this.yxldf = yxldf;
    }

    public Double getYxgt() {
        return yxgt;
    }

    public void setYxgt(Double yxgt) {
        this.yxgt = yxgt;
    }

    public Double getYxgtb() {
        return yxgtb;
    }

    public void setYxgtb(Double yxgtb) {
        this.yxgtb = yxgtb;
    }

    public Double getYxgtbdf() {
        return yxgtbdf;
    }

    public void setYxgtbdf(Double yxgtbdf) {
        this.yxgtbdf = yxgtbdf;
    }

    public Double getGctzsl() {
        return gctzsl;
    }

    public void setGctzsl(Double gctzsl) {
        this.gctzsl = gctzsl;
    }

    public Double getGctcsl() {
        return gctcsl;
    }

    public void setGctcsl(Double gctcsl) {
        this.gctcsl = gctcsl;
    }

    public Double getGctsl() {
        return gctsl;
    }

    public void setGctsl(Double gctsl) {
        this.gctsl = gctsl;
    }

    public Double getGctkf() {
        return gctkf;
    }

    public void setGctkf(Double gctkf) {
        this.gctkf = gctkf;
    }

    public Double getJsxgzsl() {
        return jsxgzsl;
    }

    public void setJsxgzsl(Double jsxgzsl) {
        this.jsxgzsl = jsxgzsl;
    }

    public Double getJsxgcsl() {
        return jsxgcsl;
    }

    public void setJsxgcsl(Double jsxgcsl) {
        this.jsxgcsl = jsxgcsl;
    }

    public Double getJsxgsl() {
        return jsxgsl;
    }

    public void setJsxgsl(Double jsxgsl) {
        this.jsxgsl = jsxgsl;
    }

    public Double getJsxgkf() {
        return jsxgkf;
    }

    public void setJsxgkf(Double jsxgkf) {
        this.jsxgkf = jsxgkf;
    }

    public Double getZlzdf() {
        return zlzdf;
    }

    public void setZlzdf(Double zlzdf) {
        this.zlzdf = zlzdf;
    }

    public Integer getZlpm() {
        return zlpm;
    }

    public void setZlpm(Integer zlpm) {
        this.zlpm = zlpm;
    }

    public Double getEdgt() {
        return edgt;
    }

    public void setEdgt(Double edgt) {
        this.edgt = edgt;
    }

    public Double getGt() {
        return gt;
    }

    public void setGt(Double gt) {
        this.gt = gt;
    }

    public Double getGtdf() {
        return gtdf;
    }

    public void setGtdf(Double gtdf) {
        this.gtdf = gtdf;
    }

    public Double getGtb() {
        return gtb;
    }

    public void setGtb(Double gtb) {
        this.gtb = gtb;
    }

    public Double getGtbdf() {
        return gtbdf;
    }

    public void setGtbdf(Double gtbdf) {
        this.gtbdf = gtbdf;
    }

    public Double getGtzdf() {
        return gtzdf;
    }

    public void setGtzdf(Double gtzdf) {
        this.gtzdf = gtzdf;
    }

    public Integer getGtpm() {
        return gtpm;
    }

    public void setGtpm(Integer gtpm) {
        this.gtpm = gtpm;
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

    public Double getNltdzf() {
        return nltdzf;
    }

    public void setNltdzf(Double nltdzf) {
        this.nltdzf = nltdzf;
    }

    public Integer getNltdpm() {
        return nltdpm;
    }

    public void setNltdpm(Integer nltdpm) {
        this.nltdpm = nltdpm;
    }

    public Double getCql() {
        return cql;
    }

    public void setCql(Double cql) {
        this.cql = cql;
    }

    public Double getCqldf() {
        return cqldf;
    }

    public void setCqldf(Double cqldf) {
        this.cqldf = cqldf;
    }

    public Double getXlcj() {
        return xlcj;
    }

    public void setXlcj(Double xlcj) {
        this.xlcj = xlcj;
    }

    public Double getXlcjdf() {
        return xlcjdf;
    }

    public void setXlcjdf(Double xlcjdf) {
        this.xlcjdf = xlcjdf;
    }

    public Double getBwcj() {
        return bwcj;
    }

    public void setBwcj(Double bwcj) {
        this.bwcj = bwcj;
    }

    public Double getBwcjdf() {
        return bwcjdf;
    }

    public void setBwcjdf(Double bwcjdf) {
        this.bwcjdf = bwcjdf;
    }

    public Double getYwxlzdf() {
        return ywxlzdf;
    }

    public void setYwxlzdf(Double ywxlzdf) {
        this.ywxlzdf = ywxlzdf;
    }

    public Integer getYwxlpm() {
        return ywxlpm;
    }

    public void setYwxlpm(Integer ywxlpm) {
        this.ywxlpm = ywxlpm;
    }

    public Double getTbby() {
        return tbby;
    }

    public void setTbby(Double tbby) {
        this.tbby = tbby;
    }

    public Double getKpzf() {
        return kpzf;
    }

    public void setKpzf(Double kpzf) {
        this.kpzf = kpzf;
    }

    public Integer getKpzpm() {
        return kpzpm;
    }

    public void setKpzpm(Integer kpzpm) {
        this.kpzpm = kpzpm;
    }

    public String getPdjg() {
        return pdjg;
    }

    public void setPdjg(String pdjg) {
        this.pdjg = pdjg;
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
