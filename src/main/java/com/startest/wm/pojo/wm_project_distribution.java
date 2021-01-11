package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-26-14:13
 * @描述 科研项目分配信息对象
 */
@ApiModel("科研项目分配信息对象")
public class wm_project_distribution {
    @ApiModelProperty("ID")
    private String dis_id;
    @ApiModelProperty("项目ID")
    private String project_id;
    @ApiModelProperty("部门ID")
    private String dept_id;
    @ApiModelProperty("人员ID")
    private String user_id;
    @ApiModelProperty("项目过程")
    private String project_rate;
    @ApiModelProperty("人员姓名")
    private String project_oper;
    @ApiModelProperty("工天")
    private String project_days;
    @ApiModelProperty("开始时间")
    private String project_startdate;
    @ApiModelProperty("结束时间")
    private String project_enddate;
    @ApiModelProperty("备注信息")
    private String remark;



    public void setProject_startdate(String project_startdate) {
        if (project_startdate==null||project_startdate.isEmpty()){
            this.project_startdate =null;
            return;
        }
        this.project_startdate = project_startdate;
    }

    public String getProject_enddate() {
        return project_enddate;
    }

    public void setProject_enddate(String project_enddate) {
        if (project_enddate==null||project_enddate.isEmpty()){
            this.project_enddate =null;
            return;
        }
        this.project_enddate = project_enddate;
    }


    public String getDis_id() {
        return dis_id;
    }

    public void setDis_id(String dis_id) {
        this.dis_id = dis_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProject_rate() {
        return project_rate;
    }

    public void setProject_rate(String project_rate) {
        this.project_rate = project_rate;
    }

    public String getProject_oper() {
        return project_oper;
    }

    public void setProject_oper(String project_oper) {
        this.project_oper = project_oper;
    }

    public String getProject_days() {
        return project_days;
    }

    public void setProject_days(String project_days) {
        this.project_days = project_days;
    }

    public String getProject_startdate() {
        return project_startdate;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
