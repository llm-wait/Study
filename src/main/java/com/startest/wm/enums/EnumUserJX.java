package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 10:48
 * @描述 J衔枚举
 **/
public enum EnumUserJX {
    w("无"),
    shangdengbing("上等兵"),
    xiashi("下士"),
    zhongshi("中士"),
    shangshi("上士"),
    shaowei("少尉"),
    zhongwei("中尉"),
    shangwei("上尉"),
    shaoxiao("少校"),
    zhongxiao("中校"),
    shangxiao("上校"),
    daxiao("大校"),
    sijijunshizhang("四级军士长"),
    yi("1"),
    er("2"),
    san("3"),
    si("4"),
    wu("5"),
    liu("6"),
    qi("7"),
    ba("8"),
    jiu("9"),
    shi("10"),
    shiyi("11"),
    shier("12"),
    shisan("13"),
    shisi("14"),
    shiwu("15"),
    shiliu("16"),
    shiqi("17"),
    shiba("18"),
    shijiu("19"),
    ershi("20"),
    ershiyi("21"),
    ershier("22"),
    ershisan("23"),
    ershisi("24"),
    ershiwu("25");

    private String userJX;

    EnumUserJX(String userJX) {
        this.userJX = userJX;
    }

    public String getUserJX() {
        return userJX;
    }
}
