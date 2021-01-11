package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-07-30 15:58
 * @描述 用户职称枚举
 **/
public enum  EnumUserZc {
    Wu("无"),
    ZlGcs("助理工程师"),
    Gcs("工程师"),
    GjGcs("高级工程师");

    private String userZc;

    public String getUserZc() {
        return userZc;
    }

    EnumUserZc(String userZc) {
        this.userZc = userZc;
    }
}
