package com.startest.wm.service;

import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-05-28 14:29
 * @描述 枚举数据服务接口
 **/
public interface SysEnumService {
    /**
     * @Description: 查询港口资料所属国家
     * @Param: []
     * @return: java.util.List<java.lang.String>
     **/
    List<String> queryPortCountry();

    /**
     * @Description: 查询港口资料所属大洲
     * @Param:
     * @return:
     **/
    List<String> queryPortContient();

    /**
     * @Description: 查询港口资料所属海区
     * @Param: []
     * @return: java.util.List<java.lang.String>
     **/
    List<String> queryPortSea();

    /**
     * @Description: 查询港口资料来源
     * @Param: []
     * @return: java.util.List<java.lang.String>
     **/
    List<String> queryPortDataSource();

    /**
     * @Description: 查询港口资料出版单位
     * @Param: []
     * @return: java.util.List<java.lang.String>
     **/
    List<String> queryMapPublishUnit();
}
