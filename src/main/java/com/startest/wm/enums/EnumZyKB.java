package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 13:20
 * @描述 开本枚举
 **/
public enum EnumZyKB {
    QK("全开"),
    DK("对开");

    private String kb;

    EnumZyKB(String kb) {
        this.kb = kb;
    }

    public String getKb() {
        return kb;
    }
}
