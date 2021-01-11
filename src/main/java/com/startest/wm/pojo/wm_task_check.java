package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-14:09
 * @描述 任务质检数据模型
 */
@ApiModel("任务质检数据模型")
public class wm_task_check {
    @ApiModelProperty("质检ID")
    private String check_id;
    @ApiModelProperty("任务索引ID")
    private String task_id;
    @ApiModelProperty("分配内容(海图：数字图，纸图，S57图)(书表：第一章)")
    private String distribution_type;
    @ApiModelProperty("检查者（用户名）")
    private String check_oper;
    @ApiModelProperty("图历表审查（书表无）")
    private String check_maptable;
    @ApiModelProperty("严重缺陷")
    private Integer check_error1;
    @ApiModelProperty("较重缺陷（海图无）")
    private Integer check_error2;
    @ApiModelProperty("一般缺陷")
    private Integer check_error3;
    @ApiModelProperty("轻微缺陷")
    private Integer check_error4;
    @ApiModelProperty("发现只能评不合格错误(0:否；1是)")
    private Integer check_lost;
    @ApiModelProperty("只计入本级缺陷值（0:否；1是）")
    private Integer check_mylevel;
    @ApiModelProperty("备注")
    private String remark;
//    @ApiModelProperty("是否技术修改（0:否；1是）")
//    private Integer chart_edit;
//    @ApiModelProperty("是否改成图（0:否；1是）")
//    private Integer chart_map_edit;
//    @ApiModelProperty("主要负责人")
//    private String chart_manager;
//    @ApiModelProperty("次要负责人")
//    private String chart_manager1;
    @ApiModelProperty("图幅质量(书表无)")
    private String check_mapquality;
//    @ApiModelProperty("操作者ID")
//    private String oper_id;
//    @ApiModelProperty("操作者职务")
//    private String oper_duty;
    @ApiModelProperty("操作者ID")
    private String user_id;
    @ApiModelProperty("执行单关联id")
    private String distribution_id;
    @ApiModelProperty("任务职责（组长，编辑，校对，审查等）")
    private String user_duty;


    public String getDistribution_id() {
        return distribution_id;
    }

    public void setDistribution_id(String distribution_id) {
        this.distribution_id = distribution_id;
    }

    public String getCheck_id() {
        return check_id;
    }

    public void setCheck_id(String check_id) {
        this.check_id = check_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getDistribution_type() {
        return distribution_type;
    }

    public void setDistribution_type(String distribution_type) {
        this.distribution_type = distribution_type;
    }

    public String getCheck_oper() {
        return check_oper;
    }

    public void setCheck_oper(String check_oper) {
        this.check_oper = check_oper;
    }

    public String getCheck_maptable() {
        return check_maptable;
    }

    public void setCheck_maptable(String check_maptable) {
        this.check_maptable = check_maptable;
    }

    public Integer getCheck_error1() {
        return check_error1;
    }

    public void setCheck_error1(Integer check_error1) {
        this.check_error1 = check_error1;
    }

    public Integer getCheck_error2() {
        return check_error2;
    }

    public void setCheck_error2(Integer check_error2) {
        this.check_error2 = check_error2;
    }

    public Integer getCheck_error3() {
        return check_error3;
    }

    public void setCheck_error3(Integer check_error3) {
        this.check_error3 = check_error3;
    }

    public Integer getCheck_error4() {
        return check_error4;
    }

    public void setCheck_error4(Integer check_error4) {
        this.check_error4 = check_error4;
    }

    public Integer getCheck_lost() {
        return check_lost;
    }

    public void setCheck_lost(Integer check_lost) {
        this.check_lost = check_lost;
    }

    public Integer getCheck_mylevel() {
        return check_mylevel;
    }

    public void setCheck_mylevel(Integer check_mylevel) {
        this.check_mylevel = check_mylevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCheck_mapquality() {
        return check_mapquality;
    }

    public void setCheck_mapquality(String check_mapquality) {
        this.check_mapquality = check_mapquality;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_duty() {
        return user_duty;
    }

    public void setUser_duty(String user_duty) {
        this.user_duty = user_duty;
    }
}
