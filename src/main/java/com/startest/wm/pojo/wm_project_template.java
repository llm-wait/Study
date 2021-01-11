package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-26-15:32
 * @描述 项目模板信息对象
 */
@ApiModel("项目模板信息对象")
public class wm_project_template {
    @ApiModelProperty("ID")
    private String template_id;
    @ApiModelProperty("模板名称")
    private String template_name;
    @ApiModelProperty("文件名称")
    private String filename;
    @ApiModelProperty("应用范围")
    private String template_range;
    @ApiModelProperty("作者")
    private String template_auther;
    @ApiModelProperty("上传时间")
    private String template_update;
    @ApiModelProperty("上传人")
    private String template_upoper;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("存储路径")
    private String template_path;


    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTemplate_range() {
        return template_range;
    }

    public void setTemplate_range(String template_range) {
        this.template_range = template_range;
    }

    public String getTemplate_auther() {
        return template_auther;
    }

    public void setTemplate_auther(String template_auther) {
        this.template_auther = template_auther;
    }

    public String getTemplate_update() {
        return template_update;
    }

    public void setTemplate_update(String template_update) {
        this.template_update = template_update;
    }

    public String getTemplate_upoper() {
        return template_upoper;
    }

    public void setTemplate_upoper(String template_upoper) {
        this.template_upoper = template_upoper;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTemplate_path() {
        return template_path;
    }

    public void setTemplate_path(String template_path) {
        this.template_path = template_path;
    }
}
