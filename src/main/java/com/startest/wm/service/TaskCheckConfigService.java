package com.startest.wm.service;

import com.startest.wm.enums.EnumCheckConfigType;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-09-18-13:27
 * @描述 质控参数配置接口
 */
public interface TaskCheckConfigService {

    /**
     * 配置常量参数
     */
    //海图严重错误
    Double CHART_SERIOUS = 9.0;

    //海图一般错误
    Double CHART_COMMONLY = 4.0;

    //海图轻微错误
    Double CHART_SLIGHT = 1.0;

    //书表严重错误
    Double BOOK_SERIOUS = 2.0;

    //书表较严重错误
    Double BOOK_LESS_SERIOUS = 1.0;

    //书表一般错误
    Double BOOK_COMMONLY = 0.5;

    //书表轻微错误
    Double BOOK_SLIGHT = 0.2;

    /**
     * 以下配置变量参数
     */

    /**
     * 获取质控的配置参数值
     *
     * @param strType 配置枚举项
     * @return
     */
    Double GetCheckConfigParam(EnumCheckConfigType strType);

    /**
     * 设置质控的参数配置数值
     *
     * @param strType 配置枚举项
     * @return
     */
    boolean SetCheckConfigParam(EnumCheckConfigType strType, Double value);
}
