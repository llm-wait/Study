package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 10:33
 * @描述 港口资料任务状态枚举
 **/
public enum EnumPortDataState {
    YCB("已出版"),
    JHXD("计划下达"),
    ZZZYZ("正在作业中"),
    YZF("已作废"),
    XB("筹划");

    private String portDataState;

    EnumPortDataState(String portDataState) {
        this.portDataState = portDataState;
    }

    public String getPortDataState() {
        return portDataState;
    }
}
