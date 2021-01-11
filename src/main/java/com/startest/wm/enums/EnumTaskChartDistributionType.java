package com.startest.wm.enums;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-23-11:16
 * @描述 海图任务职责分类
 */
public enum EnumTaskChartDistributionType {
    /**
     * 组长
     */
    ZZ("组长"),
    /**
     * 编辑设计
     */
    BJSJ("编辑设计"),
    /**
     * 作业
     */
    ZY("作业"),
    /**
     * 组校
     */
    ZJ("组校"),
    /**
     * 校对
     */
    JD("校对"),
    /**
     * 编辑审查
     */
    BJSC("编辑审查"),
    /**
     * 验收
     */
    YS("验收"),
    /**
     * 报局
     */
    BJ("报局"),
    /**
     * 入库、归档、进厂
     */
    RK("入库、归档、进厂");

    /**
     * 任务分配图类型
     */
    private String taskChartDistributionType;

    EnumTaskChartDistributionType(String taskChartDistributionType) {
        this.taskChartDistributionType = taskChartDistributionType;
    }

    public String getTaskChartDistributionType() {
        return taskChartDistributionType;
    }
}
