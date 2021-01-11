package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 13:16
 * @描述 成图尺寸枚举
 **/
public enum EnumZyCTCC {
    QK("全开"),
    DK("对开");

    private String ctcc;

    EnumZyCTCC(String ctcc) {
        this.ctcc = ctcc;
    }

    public String getCtcc() {
        return ctcc;
    }
}
