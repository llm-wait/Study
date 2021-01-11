package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-06-23 16:12
 * @描述 系统登录账户对象
 **/
@ApiModel("系统登录账户对象")
public class sys_login {
    @ApiModelProperty("唯一ID")
    private String id;
    @ApiModelProperty("登录名")
    private String login_name;
    @ApiModelProperty("登录密码")
    private String login_pwd;
    @ApiModelProperty("所属科室ID")
    private String dept_id;
    @ApiModelProperty("所属科室名称")
    private String dept_name;
    @ApiModelProperty("角色名称")
    private String role_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
