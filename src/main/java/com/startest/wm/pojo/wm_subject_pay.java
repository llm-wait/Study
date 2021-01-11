package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-26-14:51
 * @描述 项目支出信息对象
 */
@ApiModel("项目支出对象")
public class wm_subject_pay {
    @ApiModelProperty("支出ID")
    private String pay_id;
    @ApiModelProperty("项目ID")
    private String project_id;
    @ApiModelProperty("科目ID")
    private String subject_id;
    @ApiModelProperty("支出时间")
    private String pay_date;
    @ApiModelProperty("支出金额")
    private String pay_amount;
    @ApiModelProperty("支出用途")
    private String pay_use;
    @ApiModelProperty("备注")
    private String remark;

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getPay_use() {
        return pay_use;
    }

    public void setPay_use(String pay_use) {
        this.pay_use = pay_use;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
