package com.startest.wm.enums;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 11:35
 * @描述 数据源类型枚举
 **/
public enum DataSourceType {
    
    POSTGRESQL("postgresql"),
    ORACLE("oracle"),
    MYSQL("mysql");

    private String name;

    DataSourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
