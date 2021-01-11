package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 13:26
 * @描述 彩色样枚举
 **/
public enum EnumZyCSY {
    //默认1
    ONE(1),
    TWO(0);

    private int csy;

    EnumZyCSY(int csy) {
        this.csy = csy;
    }

    public int getCsy() {
        return csy;
    }
}
