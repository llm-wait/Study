package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 9:34
 * @描述 海图资料类型枚举
 **/
public enum EnumMapDataType {
    GWHQJYHT("国外海区军用海图"),
    GWHQHT("国外海区海图"),
    ZGHQJYHT("中国海区军用海图"),
    ZGHQHT("中国海区民用海图");

    private String mapType;

    EnumMapDataType(String mapType) {
        this.mapType = mapType;
    }

    public String getMapType() {
        return this.mapType;
    }
}
