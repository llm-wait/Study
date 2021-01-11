package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 11:03
 * @描述 是否婚配枚举
 **/
public enum EnumUserSfhp {
    BaoMi("保密"),
    WeiHun("未婚"),
    YiHun("已婚");

    private String userSfhp;

    EnumUserSfhp(String sfhp) {
        this.userSfhp = sfhp;
    }

    public String getUserSfhp() {
        return userSfhp;
    }
}
