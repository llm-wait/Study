package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-07-03 14:54
 * @描述 地图相关进厂单（保障部队）
 **/
@ApiModel("地图相关进厂单")
public class jcd_mapb {
    @ApiModelProperty("唯一ID")
    private String id;//唯一ID
    @ApiModelProperty("任务索引ID")
    private String task_index_id;//任务索引ID
    @ApiModelProperty("任务年份")
    private Integer task_year;
    @ApiModelProperty("任务类型")
    private String task_type;//任务类型
    @ApiModelProperty("任务名称")
    private String task_name;//任务名称
    @ApiModelProperty("任务状态")
    private String task_state;
    @ApiModelProperty("编号")
    private Integer bh;//编号
    @ApiModelProperty("图号")
    private String map_code;//图号
    @ApiModelProperty("图名")
    private String map_name;//图名
    @ApiModelProperty("室别")
    private String sb;//室别
    @ApiModelProperty("横竖幅")
    private String hsf;//横竖幅
    @ApiModelProperty("成图尺寸")
    private String ctcc;//成图尺寸
    @ApiModelProperty("编辑员")
    private String bjy;//编辑员
    @ApiModelProperty("作业员")
    private  String zyy;//作业员
    @ApiModelProperty("验收员")
    private  String ysy;//验收员
    @ApiModelProperty("印色")
    private  String ys;//印色
    @ApiModelProperty("印色数")
    private  String yss;//印色数
    @ApiModelProperty("用纸")
    private  String yz;//用纸
    @ApiModelProperty("开本")
    private  String kb;//开本
    @ApiModelProperty("张数")
    private  String zs;//张数
    @ApiModelProperty("密级")
    private String mj;//密级
    @ApiModelProperty("出版时间")
    private String cbsj;//出版时间
    @ApiModelProperty("出版时间")
    private String wcsj;//完成时间
    @ApiModelProperty("版次")
    private String bc;//版次
    @ApiModelProperty("印次")
    private String yc;//印次
    @ApiModelProperty("覆膜")
    private String fm;//覆膜
    @ApiModelProperty("彩色样")
    private String csy;//彩色样
    @ApiModelProperty("用纸令数")
    private String yzls;//用纸令数
    @ApiModelProperty("进厂日期")
    private String jcrq;//进厂日期
    @ApiModelProperty("出厂日期")
    private String ccrq;//出厂日期
    @ApiModelProperty("备注")
    private String remark;//备注
    @ApiModelProperty("开单人")
    private String kdr;//开单人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
    }

    public Integer getTask_year() {
        return task_year;
    }

    public void setTask_year(Integer task_year) {
        this.task_year = task_year;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_state() {
        return task_state;
    }

    public void setTask_state(String task_state) {
        this.task_state = task_state;
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public String getMap_code() {
        return map_code;
    }

    public void setMap_code(String map_code) {
        this.map_code = map_code;
    }

    public String getMap_name() {
        return map_name;
    }

    public void setMap_name(String map_name) {
        this.map_name = map_name;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getHsf() {
        return hsf;
    }

    public void setHsf(String hsf) {
        this.hsf = hsf;
    }

    public String getCtcc() {
        return ctcc;
    }

    public void setCtcc(String ctcc) {
        this.ctcc = ctcc;
    }

    public String getBjy() {
        return bjy;
    }

    public void setBjy(String bjy) {
        this.bjy = bjy;
    }

    public String getZyy() {
        return zyy;
    }

    public void setZyy(String zyy) {
        this.zyy = zyy;
    }

    public String getYsy() {
        return ysy;
    }

    public void setYsy(String ysy) {
        this.ysy = ysy;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getYss() {
        return yss;
    }

    public void setYss(String yss) {
        this.yss = yss;
    }

    public String getYz() {
        return yz;
    }

    public void setYz(String yz) {
        this.yz = yz;
    }

    public String getKb() {
        return kb;
    }

    public void setKb(String kb) {
        this.kb = kb;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getMj() {
        return mj;
    }

    public void setMj(String mj) {
        this.mj = mj;
    }

    public String getCbsj() {
        return cbsj;
    }

    public void setCbsj(String cbsj) {
        this.cbsj = cbsj;
    }

    public String getWcsj() {
        return wcsj;
    }

    public void setWcsj(String wcsj) {
        this.wcsj = wcsj;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getYc() {
        return yc;
    }

    public void setYc(String yc) {
        this.yc = yc;
    }

    public String getFm() {
        return fm;
    }

    public void setFm(String fm) {
        this.fm = fm;
    }

    public String getCsy() {
        return csy;
    }

    public void setCsy(String csy) {
        this.csy = csy;
    }

    public String getYzls() {
        return yzls;
    }

    public void setYzls(String yzls) {
        this.yzls = yzls;
    }

    public String getJcrq() {
        return jcrq;
    }

    public void setJcrq(String jcrq) {
        this.jcrq = jcrq;
    }

    public String getCcrq() {
        return ccrq;
    }

    public void setCcrq(String ccrq) {
        this.ccrq = ccrq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKdr() {
        return kdr;
    }

    public void setKdr(String kdr) {
        this.kdr = kdr;
    }
}
