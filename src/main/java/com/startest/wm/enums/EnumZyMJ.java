package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 13:22
 * @描述 密级枚举
 **/
public enum EnumZyMJ {
    WU("无"),
    JiMi("机密"),
    MiMi("秘密"),
    JueMi("绝密"),
    NeiBu("内部");

    private String mj;

    EnumZyMJ(String mj) {
        this.mj = mj;
    }

    public String getMj() {
        return mj;
    }
}
