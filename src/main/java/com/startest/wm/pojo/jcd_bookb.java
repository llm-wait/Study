package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-28 18:42
 * @描述 书表进厂单出单表(保障部队)
 **/
@ApiModel("书表进厂单出单表对象")
public class jcd_bookb {
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
    @ApiModelProperty("室别")
    private String sb;//室别
    @ApiModelProperty("书号")
    private String sh;//书号
    @ApiModelProperty("书名")
    private String sm;//书名
    @ApiModelProperty("编辑员")
    private String bjy;//编辑员
    @ApiModelProperty("作业员")
    private String zyy;//作业员
    @ApiModelProperty("验收员")
    private String ysy;//验收员
    @ApiModelProperty("封面印色")
    private String fmys;//封面印色
    @ApiModelProperty("封面印色数")
    private String fmyss;//封面印色数
    @ApiModelProperty("内文印色")
    private String nwys;//内文印色
    @ApiModelProperty("内文印色数")
    private String nwyss;//内文印色数
    @ApiModelProperty("封面用纸")
    private String fmyz;//封面用纸
    @ApiModelProperty("封面用纸数")
    private String fmyzs;//封面用纸数
    @ApiModelProperty("内文用纸")
    private String nwyz;//内文用纸
    @ApiModelProperty("内文用纸数")
    private String nwyzs;//内文用纸数
    @ApiModelProperty("单双面印刷")
    private String dsmys;//单双面印刷
    @ApiModelProperty("封面烫金、银")
    private String fmtjy;//封面烫金、银
    @ApiModelProperty("书脊")
    private String sj;//书脊
    @ApiModelProperty("印张")
    private String yz;//印张
    @ApiModelProperty("开本")
    private String kb;//开本
    @ApiModelProperty("印数")
    private String yinshu;//印数
    @ApiModelProperty("密级")
    private String mj;//密级
    @ApiModelProperty("出版时间")
    private String cbsj;//出版时间
    @ApiModelProperty("完成时间")
    private String wcsj;//完成时间
    @ApiModelProperty("版次")
    private String bc;//版次
    @ApiModelProperty("印次")
    private String yc;//印次
    @ApiModelProperty("压膜、油")
    private String ymy;//压膜、油
    @ApiModelProperty("样书")
    private String ys;//样书
    @ApiModelProperty("装订要求")
    private String zdyq;//装订要求
    @ApiModelProperty("用纸数量")
    private String yzsl;//用纸数量
    @ApiModelProperty("封面版数")
    private String fmbs;//封面版数
    @ApiModelProperty("内文版数")
    private String nwbs;//内文版数
    @ApiModelProperty("进厂日期")
    private String jcrq;//进厂时间
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

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
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

    public String getFmys() {
        return fmys;
    }

    public void setFmys(String fmys) {
        this.fmys = fmys;
    }

    public String getFmyss() {
        return fmyss;
    }

    public void setFmyss(String fmyss) {
        this.fmyss = fmyss;
    }

    public String getNwys() {
        return nwys;
    }

    public void setNwys(String nwys) {
        this.nwys = nwys;
    }

    public String getNwyss() {
        return nwyss;
    }

    public void setNwyss(String nwyss) {
        this.nwyss = nwyss;
    }

    public String getFmyz() {
        return fmyz;
    }

    public void setFmyz(String fmyz) {
        this.fmyz = fmyz;
    }

    public String getNwyz() {
        return nwyz;
    }

    public void setNwyz(String nwyz) {
        this.nwyz = nwyz;
    }

    public String getDsmys() {
        return dsmys;
    }

    public void setDsmys(String dsmys) {
        this.dsmys = dsmys;
    }

    public String getFmtjy() {
        return fmtjy;
    }

    public void setFmtjy(String fmtjy) {
        this.fmtjy = fmtjy;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public String getYz() {
        return yz;
    }

    public void setYz(String yz) {
        this.yz = yz;
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

    public String getYmy() {
        return ymy;
    }

    public void setYmy(String ymy) {
        this.ymy = ymy;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getZdyq() {
        return zdyq;
    }

    public void setZdyq(String zdyq) {
        this.zdyq = zdyq;
    }

    public String getYzsl() {
        return yzsl;
    }

    public void setYzsl(String yzsl) {
        this.yzsl = yzsl;
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

    public String getFmyzs() {
        return fmyzs;
    }

    public void setFmyzs(String fmyzs) {
        this.fmyzs = fmyzs;
    }

    public String getNwyzs() {
        return nwyzs;
    }

    public void setNwyzs(String nwyzs) {
        this.nwyzs = nwyzs;
    }

    public String getKb() {
        return kb;
    }

    public void setKb(String kb) {
        this.kb = kb;
    }

    public String getYinshu() {
        return yinshu;
    }

    public void setYinshu(String yinshu) {
        this.yinshu = yinshu;
    }

    public String getFmbs() {
        return fmbs;
    }

    public void setFmbs(String fmbs) {
        this.fmbs = fmbs;
    }

    public String getNwbs() {
        return nwbs;
    }

    public void setNwbs(String nwbs) {
        this.nwbs = nwbs;
    }

    public String getKdr() {
        return kdr;
    }

    public void setKdr(String kdr) {
        this.kdr = kdr;
    }
}
