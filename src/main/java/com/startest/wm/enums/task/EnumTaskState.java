package com.startest.wm.enums.task;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-18-13:26
 * @描述 任务状态枚举
 */
public enum EnumTaskState {


    /**
     * 计划下达
     */
    JHXD("计划下达"),
    /**
     * 下发
     */
    XF("下发"),
    /**
     * 任务进行
     */
    RWJX("任务进行"),
    /**
     * 任务质检
     */
    RWZJ("任务质检"),
    /**
     * 制印
     */
    ZY("制印"),
    /**
     * 已出版
     */
    YCB("已出版"),
    /**
     * 已完成
     */
    YWC("已完成");

    /**
     * 任务状态
     */
    private String taskState;

    EnumTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getTaskStateString() {
        return taskState;
    }
}
