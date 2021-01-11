package com.startest.wm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-21-13:41
 * @描述 任务监控统计模型
 */
@ApiModel("任务监控统计模型")
public class TaskResultModel {
    @ApiModelProperty("部门任务总数")
    private String task_total;
    @ApiModelProperty("部门正在做的任务数量")
    private String task_doing;
    @ApiModelProperty("进厂数量")
    private String task_enter;

    public String getTask_total() {
        return task_total;
    }

    public void setTask_total(String task_total) {
        this.task_total = task_total;
    }

    public String getTask_doing() {
        return task_doing;
    }

    public void setTask_doing(String task_doing) {
        this.task_doing = task_doing;
    }

    public String getTask_enter() {
        return task_enter;
    }

    public void setTask_enter(String task_enter) {
        this.task_enter = task_enter;
    }
}
