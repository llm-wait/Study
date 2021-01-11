package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-13 10:29
 * @描述 港口资料类型枚举
 **/
public enum EnumPortDataType {
    GWJYGK("国外军用港口"),
    GWMYGK("国外民用港口"),
    ZGJYGK("中国军用港口"),
    ZGMYGK("中国民用港口");

    private String portDataType;

    EnumPortDataType(String portDataType) {
        this.portDataType = portDataType;
    }

    public String getPortDataType() {
        return portDataType;
    }
}
