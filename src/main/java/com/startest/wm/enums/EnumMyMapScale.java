package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 10:08
 * @描述 民用海图比例尺枚举
 **/
public enum EnumMyMapScale {
    SBYQ("1:300万-1:1000万"),
    YBEB("1:100万-1:200万"),
    WSJJ("1:50万-1:99万"),
    ESSJ("1:20万-1:49万"),
    SYJ("1:10万-1:19万"),
    DYSW("大于1:10万");

    private String myMapScale;

    EnumMyMapScale(String myMapScale) {
        this.myMapScale = myMapScale;
    }

    public String getMyMapScale() {
        return myMapScale;
    }
}
