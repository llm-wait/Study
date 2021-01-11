package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 13:18
 * @描述 印色枚举
 **/
public enum EnumZyYS {
    Hei("黑"),
    ZiHuang("紫黄"),
    Lan("蓝");

    private String ys;

    EnumZyYS(String ys) {
        this.ys = ys;
    }

    public String getYs() {
        return ys;
    }
}
