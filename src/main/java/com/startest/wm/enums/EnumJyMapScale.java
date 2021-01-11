package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 10:04
 * @描述 军用海图比例尺枚举
 **/
public enum EnumJyMapScale {
    YBWSW("1:50万"),
    YBESW("1:20万"),
    YBSW("1:10万"),
    YBWW("1:5万"),
    DYYBBW("大于1:8万");

    private String jyMapScale;

    EnumJyMapScale(String jyMapScale) {
        this.jyMapScale = jyMapScale;
    }

    public String getJyMapScale() {
        return jyMapScale;
    }
}
