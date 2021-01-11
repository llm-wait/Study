package com.startest.wm.enums;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-10-10-10:01
 * @描述 质量评定状态
 */
public enum EnumTaskQualityState {
    YX("优秀"),

    HG("合格"),

    BHG("不合格");

    private String taskQualityState;

    EnumTaskQualityState(String qualityState) {
        this.taskQualityState = qualityState;
    }

    public String getTaskQualityState() {
        return taskQualityState;
    }
}
