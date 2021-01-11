package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:36
 * @描述 系统用户信息表实体
 **/
@ApiModel("系统用户对象")
public class sys_user {
    @ApiModelProperty("唯一ID")
    private String user_id;
    @ApiModelProperty("用户真实姓名")
    private String user_name;
    @ApiModelProperty("登录密码")
    private String pwd;
    @ApiModelProperty("盐值")
    private String salt;
    @ApiModelProperty("登录名")
    private String user_login_name;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("民族")
    private String mz;
    @ApiModelProperty("出生时间")
    private String cssj;
    @ApiModelProperty("籍贯")
    private String jg;
    @ApiModelProperty("来社时间")
    private String lssj;
    @ApiModelProperty("技术职务（员工档级）")
    private String jszw;
    @ApiModelProperty("定档时间")
    private String ddsj;
    @ApiModelProperty("军衔")
    private String jx;
    @ApiModelProperty("军衔时间")
    private String jxsj;
    @ApiModelProperty("所在单位")
    private String szdw;
    @ApiModelProperty("职称")
    private String zc;
    @ApiModelProperty("职称时间")
    private String zcsj;
    @ApiModelProperty("入伍时间")
    private String rwsj;
    @ApiModelProperty("婚姻状况")
    private String hyzk;
    @ApiModelProperty("政治面貌")
    private String zzmm;
    @ApiModelProperty("入党时间")
    private String rdsj;
    @ApiModelProperty("军官证号")
    private String jgzh;
    @ApiModelProperty("身份证号")
    private String sfzh;
    @ApiModelProperty("人员类别")
    private String rylb;
    @ApiModelProperty("第一学历")
    private String dyxl;
    @ApiModelProperty("第一学历院校")
    private String dyxlyx;
    @ApiModelProperty("第一学历专业")
    private String dyxlzy;
    @ApiModelProperty("第一学历毕业时间")
    private String dyxlbysj;
    @ApiModelProperty("第二学历")
    private String dexl;
    @ApiModelProperty("第二学历院校")
    private String dexlyx;
    @ApiModelProperty("第二学历专业")
    private String dexlzy;
    @ApiModelProperty("第二学历毕业时间")
    private String dexlbysj;
    @ApiModelProperty("照片")
    private String photo;
    @ApiModelProperty("是否禁用")
    private Integer sfjy;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUser_login_name() {
        return user_login_name;
    }

    public void setUser_login_name(String user_login_name) {
        this.user_login_name = user_login_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getCssj() {
        return cssj;
    }

    public void setCssj(String cssj) {
        this.cssj = cssj;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getLssj() {
        return lssj;
    }

    public void setLssj(String lssj) {
        this.lssj = lssj;
    }

    public String getJszw() {
        return jszw;
    }

    public void setJszw(String jszw) {
        this.jszw = jszw;
    }

    public String getDdsj() {
        return ddsj;
    }

    public void setDdsj(String ddsj) {
        this.ddsj = ddsj;
    }

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
    }

    public String getJxsj() {
        return jxsj;
    }

    public void setJxsj(String jxsj) {
        this.jxsj = jxsj;
    }

    public String getSzdw() {
        return szdw;
    }

    public void setSzdw(String szdw) {
        this.szdw = szdw;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getZcsj() {
        return zcsj;
    }

    public void setZcsj(String zcsj) {
        this.zcsj = zcsj;
    }

    public String getRwsj() {
        return rwsj;
    }

    public void setRwsj(String rwsj) {
        this.rwsj = rwsj;
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }

    public String getJgzh() {
        return jgzh;
    }

    public void setJgzh(String jgzh) {
        this.jgzh = jgzh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getRylb() {
        return rylb;
    }

    public void setRylb(String rylb) {
        this.rylb = rylb;
    }

    public String getDyxl() {
        return dyxl;
    }

    public void setDyxl(String dyxl) {
        this.dyxl = dyxl;
    }

    public String getDyxlyx() {
        return dyxlyx;
    }

    public void setDyxlyx(String dyxlyx) {
        this.dyxlyx = dyxlyx;
    }

    public String getDyxlzy() {
        return dyxlzy;
    }

    public void setDyxlzy(String dyxlzy) {
        this.dyxlzy = dyxlzy;
    }

    public String getDyxlbysj() {
        return dyxlbysj;
    }

    public void setDyxlbysj(String dyxlbysj) {
        this.dyxlbysj = dyxlbysj;
    }

    public String getDexl() {
        return dexl;
    }

    public void setDexl(String dexl) {
        this.dexl = dexl;
    }

    public String getDexlyx() {
        return dexlyx;
    }

    public void setDexlyx(String dexlyx) {
        this.dexlyx = dexlyx;
    }

    public String getDexlzy() {
        return dexlzy;
    }

    public void setDexlzy(String dexlzy) {
        this.dexlzy = dexlzy;
    }

    public String getDexlbysj() {
        return dexlbysj;
    }

    public void setDexlbysj(String dexlbysj) {
        this.dexlbysj = dexlbysj;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getSfjy() {
        return sfjy;
    }

    public void setSfjy(Integer sfjy) {
        this.sfjy = sfjy;
    }
}
