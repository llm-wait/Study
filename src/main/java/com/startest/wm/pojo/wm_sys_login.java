package com.startest.wm.pojo;

import java.io.Serializable;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 15:32
 * @描述 系统登录用户类
 **/

public class wm_sys_login implements Serializable {

    private  static final  long serialVersionUID=7760614561073458247L;

    private String id;//唯一ID
    private String login_name;//登录名称
    private String login_pwd;//登录ID
    private Integer is_admin;//是否是管理员
    private String dept_id;//部门ID
    private String dept_name;//部门名称
    private Integer power_grade;//岗位级别

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

    public Integer getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Integer is_admin) {
        this.is_admin = is_admin;
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

    public Integer getPower_grade() {
        return power_grade;
    }

    public void setPower_grade(Integer power_grade) {
        this.power_grade = power_grade;
    }

    public wm_sys_login(String id, String login_name, String login_pwd, Integer is_admin, String dept_id, String dept_name, Integer power_grade) {
        this.id = id;
        this.login_name = login_name;
        this.login_pwd = login_pwd;
        this.is_admin = is_admin;
        this.dept_id = dept_id;
        this.dept_name = dept_name;
        this.power_grade = power_grade;
    }

    @Override
    public String toString() {
        return "wm_sys_login{" +
                "id='" + id + '\'' +
                ", login_name='" + login_name + '\'' +
                ", login_pwd='" + login_pwd + '\'' +
                ", is_admin=" + is_admin +
                ", dept_id='" + dept_id + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", power_grade=" + power_grade +
                '}';
    }
}
