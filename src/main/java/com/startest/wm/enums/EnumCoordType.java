package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-13 11:06
 * @描述 坐标格式类型枚举
 **/
public enum EnumCoordType {
    DMSS("DMSS"),
    DMS("DMS"),
    DM("DM"),
    DD("DD");

    private String coordType;

    EnumCoordType(String coordType) {
        this.coordType = coordType;
    }

    public String getCoordType() {
        return coordType;
    }
}
