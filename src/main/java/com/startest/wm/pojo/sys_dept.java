package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:23
 * @描述 部门信息表数据信息实体
 **/
@ApiModel("部门对象")
public class sys_dept {
    @ApiModelProperty("部门唯一ID")
    private String dept_id;
    @ApiModelProperty("部门父级ID")
    private String dept_pid;
    @ApiModelProperty("部门名称")
    private String dept_name;
    @ApiModelProperty("部门顺序")
    private Integer dept_order;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("部门标记：是否参与作业任务和考评，机关部门不参与任务和考评")
    private Integer flag;
    @ApiModelProperty("任务ID")
    private String task_index_id;

    private List<sys_dept> childDepts;

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

    public String getDept_pid() {
        return dept_pid;
    }

    public void setDept_pid(String dept_pid) {
        this.dept_pid = dept_pid;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public Integer getDept_order() {
        return dept_order;
    }

    public void setDept_order(Integer dept_order) {
        this.dept_order = dept_order;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<sys_dept> getChildDepts() {
        return childDepts;
    }

    public void setChildDepts(List<sys_dept> childDepts) {
        this.childDepts = childDepts;
    }
}
