package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-09-18-13:12
 * @描述 质控评审参数对象
 */
@ApiModel("质控评审参数数据模型")
public class wm_task_config {
    @ApiModelProperty("配置参数ID")
    private String config_id;
    @ApiModelProperty("配置参数名称")
    private String config_name;
    @ApiModelProperty("配置参数值")
    private String config_value;

    public String getConfig_id() {
        return config_id;
    }

    public void setConfig_id(String config_id) {
        this.config_id = config_id;
    }

    public String getConfig_name() {
        return config_name;
    }

    public void setConfig_name(String config_name) {
        this.config_name = config_name;
    }

    public String getConfig_value() {
        return config_value;
    }

    public void setConfig_value(String config_value) {
        this.config_value = config_value;
    }
}
