package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 11:22
 * @描述 用户学历枚举
 **/
public enum EnumUserXl {
    WU("无"),
    BoShi("博士"),
    ShuoShi("硕士"),
    BenKe("本科"),
    DaZhuan("大专"),
    ZhongZhuan("中专"),
    ZhiZhuan("职专"),
    GaoZhong("高中"),
    ChuZhong("初中");

    private String userXl;

    EnumUserXl(String userXl) {
        this.userXl = userXl;
    }

    public String getUserXl() {
        return userXl;
    }
}
