package com.startest.wm.model;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-06-19 16:51
 * @描述 用户查询模型
 **/
public class UserSearchModel {
    private String user_name;
    private String rylb;
    private Integer sfjy;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRylb() {
        return rylb;
    }

    public void setRylb(String rylb) {
        this.rylb = rylb;
    }

    public Integer getSfjy() {
        return sfjy;
    }

    public void setSfjy(Integer sfjy) {
        this.sfjy = sfjy;
    }
}
