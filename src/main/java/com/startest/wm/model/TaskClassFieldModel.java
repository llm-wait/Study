package com.startest.wm.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author 杨世凯
 * @公司 北京星天科技
 * @创建时间 2020-05-22-20:39
 * @描述 任务种类字段数据模型
 */
public class TaskClassFieldModel {
    @ApiModelProperty("字段别名")
    private String label;
    @ApiModelProperty("字段名称")
    private String prop;
    @ApiModelProperty("是否显示")
    private boolean visible;


    public TaskClassFieldModel() {
    }


    /**有参赋值，visible默认为true
     * @param label
     * @param prop
     */
    public TaskClassFieldModel(String label, String prop) {
        this.label = label;
        this.prop = prop;
        this.visible=true;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }
}
