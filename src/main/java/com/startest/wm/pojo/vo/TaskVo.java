package com.startest.wm.pojo.vo;

/**
 * @author skj
 * @date 2020/12/31 0031 10:02
 * 描述：通用任务
 *
 */
public class TaskVo {
    /**
     * 任务id
     */
    private String index_id;
    /**
     * 任务分类id
     */
    private String task_class_id;

    /**
     * 任务分类名称
     */
    private String task_type;

    /**
     * 任务年份
     */
    private String task_year;
    /**
     * 任务名称
     */
    private String task_name;
    /**
     * 任务备注
     */
    private String task_remark;
    /**
     * 任务状态
     */
    private String task_state;

    /**
     * 任务分配部门id
     */
    private String dept_id;
    /**
     * 任务分配部门名称
     */
    private String dept_name;
    /**
     * 是否分配部门
     */
    private Integer is_task_dept;

    /**
     * 任务验收时间
     */
    private String task_end_date;

    public String getTask_end_date() {
        return task_end_date;
    }

    public void setTask_end_date(String task_end_date) {
        this.task_end_date = task_end_date;
    }


    public String getIndex_id() {
        return index_id;
    }

    public void setIndex_id(String index_id) {
        this.index_id = index_id;
    }

    public String getTask_class_id() {
        return task_class_id;
    }

    public void setTask_class_id(String task_class_id) {
        this.task_class_id = task_class_id;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_year() {
        return task_year;
    }

    public void setTask_year(String task_year) {
        this.task_year = task_year;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_remark() {
        return task_remark;
    }

    public void setTask_remark(String task_remark) {
        this.task_remark = task_remark;
    }

    public String getTask_state() {
        return task_state;
    }

    public void setTask_state(String task_state) {
        this.task_state = task_state;
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

    public Integer getIs_task_dept() {
        return is_task_dept;
    }

    public void setIs_task_dept(Integer is_task_dept) {
        this.is_task_dept = is_task_dept;
    }
}
