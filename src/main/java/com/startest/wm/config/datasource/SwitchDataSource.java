package com.startest.wm.config.datasource;

import com.startest.wm.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 11:29
 * @描述 切换数据源注解
 **/


@Target({ElementType.TYPE,ElementType.METHOD})//指明此自定义注解只能用在方法上
@Retention(RetentionPolicy.RUNTIME)//指明此自定义注解是运行时注解
@Documented
public @interface SwitchDataSource {
    /**
     * @Description: 指定默认数据库是Postgresql
     * @return: com.startest.wm.enums.DataSourceType
     **/
    DataSourceType value() default DataSourceType.POSTGRESQL;
}
