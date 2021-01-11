package com.startest.wm.enums.task;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-18-13:34
 * @描述 任务类型枚举
 */
public enum EnumTaskType {

    /**
     * 制图任务
     */
    ZTRW("制图任务"),
    /**
     * 书表任务
     */
    SBRW("书表任务"),
    /**
     * 年度任务
     */
    NDRW("年度任务"),
    /**
     * 日常任务
     */
    RCRW("日常任务"),
    /**
     * 应急保障任务
     */
    YJBZRW("应急保障任务");

    /**
     * 任务类型
     */
    private final String taskType;

    EnumTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskTypeString() {
        return taskType;
    }
}
