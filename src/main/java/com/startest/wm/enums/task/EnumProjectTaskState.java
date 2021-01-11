package com.startest.wm.enums.task;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-08-17-15:59
 * @描述 科研任务状态
 */
public enum EnumProjectTaskState {
    DCL("待处理"),
    ZZCL("正在处理"),
    YWC("已完成");

    /**
     * 产品状态
     */
    private String projectTaskState;

    EnumProjectTaskState(String projectTaskState) {
        this.projectTaskState = projectTaskState;
    }

    public String getProjectStateString() {
        return projectTaskState;
    }
}
