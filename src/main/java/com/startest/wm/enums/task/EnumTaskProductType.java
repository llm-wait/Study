package com.startest.wm.enums.task;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-23-11:02
 * @描述 任务产品类型
 */
public enum EnumTaskProductType {
    /**
     * 纸图
     */
    ZT("纸图"),
    /**
     * 数字图
     */
    SZT("数字图"),
    /**
     * S57图
     */
    S57("S57图"),
    /**
     * EPS
     */
    EPS("EPS"),
    /**
     * 图集插图
     */
    TJCT("图集插图");

    /**
     * 产品类型
     */
    private final String taskProductType;

    EnumTaskProductType(String taskProductType) {
        this.taskProductType = taskProductType;
    }

    public String getTaskProductType() {
        return taskProductType;
    }
}
