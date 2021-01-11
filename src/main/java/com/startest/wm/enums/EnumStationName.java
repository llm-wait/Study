package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 10:29
 * @描述 港口资料类型枚举
 **/
public enum EnumStationName {
    BJ("编辑"),
    ZZ("组长"),
    ZYY("作业员");

    /**
     *职务分类
     */
    private String StationName;

    EnumStationName(String StationName) {
        this.StationName = StationName;
    }

    public String getStationName() {
        return StationName;
    }

}
