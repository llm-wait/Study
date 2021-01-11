package com.startest.wm.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 13:16
 * @描述 动态数据源类
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    /**
     * @Description: 获取当前线程的数据源名称
     * @Param: []
     * @return: java.lang.Object
     **/  
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContext.getDataSource();
    }
}
