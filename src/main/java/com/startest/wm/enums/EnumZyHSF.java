package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 13:12
 * @描述 横竖幅枚举
 **/
public enum EnumZyHSF {
    HF("横幅"),
    SF("竖幅");

    private String hsf;

    EnumZyHSF(String hsf) {
        this.hsf = hsf;
    }

    public String getHsf() {
        return hsf;
    }
}
