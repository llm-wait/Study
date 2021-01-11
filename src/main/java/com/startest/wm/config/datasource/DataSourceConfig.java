package com.startest.wm.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.startest.wm.enums.DataSourceType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 16:00
 * @描述 动态数据源配置类
 **/
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "dataSourcePostgreSql")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource dataSourcePostgreSql() {
        return DruidDataSourceBuilder.create().build();
    }


    /*@Bean(name = "dataSourceMySql")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource dataSourceMySql(){
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "dataSourceOracle")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource dataSourceOracle(){
        return DruidDataSourceBuilder.create().build();
    }*/

    @Bean(name = "dynamicDS")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource postgreSql = dataSourcePostgreSql();
//        DataSource mySql = dataSourceMySql();
//        DataSource oracle = dataSourceOracle();
        dynamicDataSource.setDefaultTargetDataSource(postgreSql);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(DataSourceType.POSTGRESQL.getName(), postgreSql);
//        hashMap.put(DataSourceType.MYSQL.getName(),mySql);
//        hashMap.put(DataSourceType.ORACLE.getName(),oracle);
        dynamicDataSource.setTargetDataSources(hashMap);
        return dynamicDataSource;
    }
}
