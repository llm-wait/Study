package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 13:24
 * @描述 是否覆膜枚举
 **/
public enum EnumZySFFM {
    Yes("是"),
    No("否");

    private String sffm;

    EnumZySFFM(String sffm) {
        this.sffm = sffm;
    }

    public String getSffm() {
        return sffm;
    }
}
