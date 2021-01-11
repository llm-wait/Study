package com.startest.wm.enums;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-23-13:13
 * @描述 书表任务职务分类
 */
public enum EnumTaskBookDistributionType {
    ZZ("组长"),
    ZLFX("资料分析"),
    BJSJ("编辑设计"),
    ZLFY("资料翻译"),
    ZY("作业"),
    ZJ("组校"),
    JD("校对"),
    BJSC("编辑审查");

    private String taskBookDistributionType;

    EnumTaskBookDistributionType(String taskBookDistributionType) {
        this.taskBookDistributionType = taskBookDistributionType;
    }

    public String getTaskBookDistributionType() {
        return taskBookDistributionType;
    }
}
