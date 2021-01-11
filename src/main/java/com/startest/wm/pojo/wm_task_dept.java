package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-04-08-9:48
 * @描述 分配部门表
 */
@ApiModel("分配部门表")
public class wm_task_dept {
    @ApiModelProperty("分配部门id")
    private String task_dept_id;
    @ApiModelProperty("任务索引id")
    private String task_index_id;
    @ApiModelProperty("部门id")
    private String dept_id;
    @ApiModelProperty("部门名称")
    private String dept_name;
    @ApiModelProperty("操作人")
    private String task_dept_oper;
    @ApiModelProperty("操作时间")
    private String task_dept_date;
    @ApiModelProperty("备注")
    private String remark;


    /**时间处理
     * @param task_dept_date
     */
    public void setTask_dept_date(String task_dept_date) {
        if (task_dept_date == null || task_dept_date.isEmpty()) {
            this.task_dept_date = null;
            return;
        }
        this.task_dept_date = task_dept_date;
    }

    public String getTask_dept_id() {
        return task_dept_id;
    }

    public void setTask_dept_id(String task_dept_id) {
        this.task_dept_id = task_dept_id;
    }

    public String getTask_index_id() {
        return task_index_id;
    }

    public void setTask_index_id(String task_index_id) {
        this.task_index_id = task_index_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getTask_dept_oper() {
        return task_dept_oper;
    }

    public void setTask_dept_oper(String task_dept_oper) {
        this.task_dept_oper = task_dept_oper;
    }

    public String getTask_dept_date() {
        return task_dept_date;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
