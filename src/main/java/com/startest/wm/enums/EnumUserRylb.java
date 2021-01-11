package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 11:00
 * @描述 人员类别枚举
 **/
public enum EnumUserRylb {
    ShiBing("士兵"),
    XYJSGB("现役技术干部"),
    WenZhi("文职"),
    YuGong("员工"),
    FPRY("返聘人员");

    private String userRylb;

    EnumUserRylb(String userRylb) {
        this.userRylb = userRylb;
    }

    public String getUserRylb() {
        return userRylb;
    }
}
