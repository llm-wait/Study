package com.startest.wm.model;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-14 15:18
 * @描述 用户部门相关信息临时对象模型
 **/
public class UserDept {
    private String user_id;
    private String user_name;
    private String dept_id;
    private String dept_name;

    public UserDept(String user_id, String user_name, String dept_id, String dept_name) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.dept_id = dept_id;
        this.dept_name = dept_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
}
