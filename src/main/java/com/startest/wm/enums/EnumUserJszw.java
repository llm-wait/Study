package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 10:40
 * @描述 用户技术职务枚举
 **/
public enum EnumUserJszw {
    WU("无"),
    JS13J("技术13级"),
    JS12J("技术12级"),
    JS11J("技术11级"),
    JS10J("技术10级"),
    JS9J("技术9级"),
    JS8J("技术8级"),
    JS7J("技术7级"),
    JS6J("技术6级"),
    JS5J("技术5级"),
    JS4J("技术4级"),
    JS3J("技术3级"),
    JS2J("技术2级"),
    JS1J("技术1级"),
    CJ1D("初级一档"),
    CJ2D("初级二档"),
    CJ3D("初级三档"),
    ZJ1D("中级一档"),
    ZJ2D("中级二档"),
    ZJ3D("中级三档"),
    GJ1D("高级一档"),
    GJ2D("高级二档"),
    GJ3D("高级三档");

    private String userJszw;

    EnumUserJszw(String userJszw) {
        this.userJszw = userJszw;
    }

    public String getUserJszw() {
        return userJszw;
    }
}
