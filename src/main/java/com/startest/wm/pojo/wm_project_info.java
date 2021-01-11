package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-26-13:21
 * @描述 科研项目信息对象
 */
@ApiModel("科研项目信息对象")
public class wm_project_info {
    @ApiModelProperty("项目唯一ID")
    private String id;
    @ApiModelProperty("项目索引ID")
    private String index_id;
    @ApiModelProperty("年份")
    private Integer project_year;
    @ApiModelProperty("项目名称")
    private String project_name;
    @ApiModelProperty("项目来源")
    private String project_source;
    @ApiModelProperty("项目周期")
    private String project_cycle;
    @ApiModelProperty("负责单位")
    private String project_unit;
    @ApiModelProperty("项目负责人")
    private String project_leader;
    @ApiModelProperty("技术带头人")
    private String technical_leader;
    @ApiModelProperty("项目成员")
    private String project_members;
    @ApiModelProperty("开始时间")
    private String project_startdate;
    @ApiModelProperty("结束时间")
    private String project_enddate;
    @ApiModelProperty("总经费")
    private Double total_money;
    @ApiModelProperty("已下经费")
    private Double pay_money;
    /*@ApiModelProperty("到账率")
    private String arrival_rate;*/
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("项目状态：待处理，处理中，已完成")
    private String project_state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex_id() {
        return index_id;
    }

    public void setIndex_id(String index_id) {
        this.index_id = index_id;
    }

    public Integer getProject_year() {
        return project_year;
    }

    public void setProject_year(Integer project_year) {
        this.project_year = project_year;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_source() {
        return project_source;
    }

    public void setProject_source(String project_source) {
        this.project_source = project_source;
    }

    public String getProject_cycle() {
        return project_cycle;
    }

    public void setProject_cycle(String project_cycle) {
        this.project_cycle = project_cycle;
    }

    public String getProject_unit() {
        return project_unit;
    }

    public void setProject_unit(String project_unit) {
        this.project_unit = project_unit;
    }

    public String getProject_leader() {
        return project_leader;
    }

    public void setProject_leader(String project_leader) {
        this.project_leader = project_leader;
    }

    public String getTechnical_leader() {
        return technical_leader;
    }

    public void setTechnical_leader(String technical_leader) {
        this.technical_leader = technical_leader;
    }

    public String getProject_members() {
        return project_members;
    }

    public void setProject_members(String project_members) {
        this.project_members = project_members;
    }

    public String getProject_startdate() {
        return project_startdate;
    }

    public void setProject_startdate(String project_startdate) {
        this.project_startdate = project_startdate;
    }

    public Double getTotal_money() {
        return total_money;
    }

    public void setTotal_money(Double total_money) {
        this.total_money = total_money;
    }

    public String getProject_enddate() {
        return project_enddate;
    }

    public void setProject_enddate(String project_enddate) {
        this.project_enddate = project_enddate;
    }

    public Double getPay_money() {
        return pay_money;
    }

    public void setPay_money(Double pay_money) {
        this.pay_money = pay_money;
    }

    /*public String getArrival_rate() {
        return arrival_rate;
    }

    public void setArrival_rate(String arrival_rate) {
        this.arrival_rate = arrival_rate;
    }*/

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProject_state() {
        return project_state;
    }

    public void setProject_state(String project_state) {
        this.project_state = project_state;
    }
}
