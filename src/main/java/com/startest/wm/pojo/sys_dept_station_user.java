package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-26 18:01
 * @描述 岗位人员关系表
 **/
@ApiModel("部门岗位人员关系对象")
public class sys_dept_station_user {
    @ApiModelProperty("唯一ID")
    private String id;
    @ApiModelProperty("部门ID")
    private String dept_id;
    @ApiModelProperty("岗位ID")
    private String station_id;
    @ApiModelProperty("用户ID")
    private String user_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
