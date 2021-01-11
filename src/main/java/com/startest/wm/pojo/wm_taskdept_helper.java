package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-13-11:54
 * @描述 任务分配部门帮助模型
 */
@ApiModel("分配部门帮助Model")
public class wm_taskdept_helper {
    @ApiModelProperty("任务索引id")
    private String task_index_id;
    @ApiModelProperty("分配部门信息列表")
    private List<wm_task_dept> data_list;

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
    }

    public List<wm_task_dept> getData_list() {
        return data_list;
    }

    public void setData_list(List<wm_task_dept> data_list) {
        this.data_list = data_list;
    }
}
