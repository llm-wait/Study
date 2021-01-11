package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-07-07 15:42
 * @描述 登录角色枚举
 **/
public enum EnumLoginRole {
    CG("超级管理员"),
    Admin("管理员"),
    JHCM("jihuacanmou"),
    ZKCM("zhikongcanmou"),
    ZKOperator("zhikongchucaozuoyuan"),
    ZYCM("zhiyincanmou"),
    KYCM("keyancanmou"),
    ZYSZR("zuoyeshizhuren"),
    ZYSOperator("zuoyeshicaozuoyuan");

    private String name;

    EnumLoginRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
