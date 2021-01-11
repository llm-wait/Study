package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:43
 * @描述
 **/
@ApiModel("任务种类对象")
public class wm_task_class {
    @ApiModelProperty("种类id")
    private String class_id;
    @ApiModelProperty("父项id")
    private String parent_id;
    @ApiModelProperty("分类名称")
    private String class_name;
    @ApiModelProperty("分类附属信息（任务索引标记为：任务）")
    private String class_tag;
    @ApiModelProperty("备注")
    private String class_remark;
    @ApiModelProperty("图标名称")
    private String class_icon;
    @ApiModelProperty("次序")
    private Integer class_order;
    @ApiModelProperty("种类年份")
    private String class_year;
    @ApiModelProperty("种类类型：0筹划分类；1：任务过程分类")
    private Integer class_type;
    @ApiModelProperty("孩子列表")
    private List<wm_task_class> childList;
    @ApiModelProperty("资料ID")
    private String data_id;

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }


    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_tag() {
        return class_tag;
    }

    public void setClass_tag(String class_tag) {
        this.class_tag = class_tag;
    }

    public String getClass_remark() {
        return class_remark;
    }

    public void setClass_remark(String class_remark) {
        this.class_remark = class_remark;
    }

    public String getClass_icon() {
        return class_icon;
    }

    public void setClass_icon(String class_icon) {
        this.class_icon = class_icon;
    }

    public Integer getClass_order() {
        return class_order;
    }

    public void setClass_order(Integer class_order) {
        this.class_order = class_order;
    }

    public String getClass_year() {
        return class_year;
    }

    public void setClass_year(String class_year) {
        this.class_year = class_year;
    }

    public Integer getClass_type() {
        return class_type;
    }

    public void setClass_type(Integer class_type) {
        this.class_type = class_type;
    }

    public List<wm_task_class> getChildList() {
        return childList;
    }

    public void setChildList(List<wm_task_class> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "wm_task_class{" +
                "class_id='" + class_id + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", class_name='" + class_name + '\'' +
                ", class_tag='" + class_tag + '\'' +
                ", class_remark='" + class_remark + '\'' +
                ", class_icon='" + class_icon + '\'' +
                ", class_order=" + class_order +
                ", class_year='" + class_year + '\'' +
                ", class_type=" + class_type +
                ", childList=" + childList +
                ", data_id='" + data_id + '\'' +
                '}';
    }
}
