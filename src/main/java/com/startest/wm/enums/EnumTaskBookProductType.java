package com.startest.wm.enums;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-09-17-15:04
 * @描述 任务书表产品类型
 */
public enum EnumTaskBookProductType {
    HHTG("航海通告"),
    HHBB("航海表薄"),
    HHZL("航海资料"),
    YWHHTG("英文版航海通告");

    private String taskProductType;

    EnumTaskBookProductType(String taskProductType) {
        this.taskProductType = taskProductType;
    }

    public String getTaskProductType() {
        return taskProductType;
    }
}
