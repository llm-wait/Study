package com.startest.wm.config.exceptionHandler;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-03-27-10:31
 * @描述 自定义错误基础接口类
 */
public interface BaseErrorInfoInterface {
    /*
    错误码
    * */
    String getResultCode();

    /*
    错误信息
     */
    String getResultMsg();
}
