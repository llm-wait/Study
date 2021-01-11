package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 13:29
 * @描述 制印通知单表头类型枚举
 **/
public enum EnumZyFormHeaderType {
    MYHT("MYHT"),
    JYHT("JYHT"),
    MYSB("MYSB"),
    JYSB("JYSB"),
    MYHTJ("MYHT-J"),
    MYSBJ("MYSB-J"),
    UVPH("UVPH");

    private String formHeaderType;

    EnumZyFormHeaderType(String formHeaderType) {
        this.formHeaderType = formHeaderType;
    }

    public String getFormHeaderType() {
        return formHeaderType;
    }
}
