package com.startest.wm.pojo;

import com.startest.wm.utils.MyDateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-14-14:07
 * @描述 编务信息数据模型
 */
@ApiModel("编务数据模型")
public class wm_form_index {
    @ApiModelProperty("表单ID")
    private String form_index_id;
    @ApiModelProperty("任务索引ID")
    private String index_id;
    @ApiModelProperty("关联表单ID")
    private String form_id;
    @ApiModelProperty("表单类型（书号表单、CIP数据单）")
    private String form_type;
    @ApiModelProperty("提交人")
    private String submit_oper;
    @ApiModelProperty("提交单位")
    private String submit_unit;
    @ApiModelProperty("提交时间")
    @DateTimeFormat(pattern= MyDateUtils.DATE_PATTERN_SECOND)
    private String submit_date;
    @ApiModelProperty("审核状态")
    private String examine_state;
    @ApiModelProperty("审核意见")
    private String examine_opinion;

    public String getForm_index_id() {
        return form_index_id;
    }

    public void setForm_index_id(String form_index_id) {
        this.form_index_id = form_index_id;
    }

    public String getIndex_id() {
        return index_id;
    }

    public void setIndex_id(String index_id) {
        this.index_id = index_id;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getForm_type() {
        return form_type;
    }

    public void setForm_type(String form_type) {
        this.form_type = form_type;
    }

    public String getSubmit_oper() {
        return submit_oper;
    }

    public void setSubmit_oper(String submit_oper) {
        this.submit_oper = submit_oper;
    }

    public String getSubmit_unit() {
        return submit_unit;
    }

    public void setSubmit_unit(String submit_unit) {
        this.submit_unit = submit_unit;
    }

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public String getExamine_state() {
        return examine_state;
    }

    public void setExamine_state(String examine_state) {
        this.examine_state = examine_state;
    }

    public String getExamine_opinion() {
        return examine_opinion;
    }

    public void setExamine_opinion(String examine_opinion) {
        this.examine_opinion = examine_opinion;
    }
}
