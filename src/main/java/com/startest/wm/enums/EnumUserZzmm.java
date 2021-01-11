package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 11:05
 * @描述 用户政治面貌枚举
 **/
public enum EnumUserZzmm {
    QunZhong("群众"),
    TuanYuan("团员"),
    DangYuan("党员");

    private String userZzmm;

    EnumUserZzmm(String userZzmm) {
        this.userZzmm = userZzmm;
    }

    public String getUserZzmm() {
        return userZzmm;
    }
}
