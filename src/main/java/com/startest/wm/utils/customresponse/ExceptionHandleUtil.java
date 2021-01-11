package com.startest.wm.utils.customresponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-30 16:40
 * @描述 控制器异常捕获类
 **/
public class ExceptionHandleUtil {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandleUtil.class);

    public static <T> RestResponse<T> handle(Exception e) {
        //判断是否是自定义错误
        if (e instanceof CustomException) {
            logger.error("系统异常：", e);
            CustomException customException = (CustomException) e;
            return RestResponseUtil.error(customException.getCode(), customException.getMessage());
        } else {
            //未知错误
            return RestResponseUtil.error(RestResponseCode.UNDEFINED_ERROR.getCode(), RestResponseCode.UNDEFINED_ERROR.getMsg());
        }
    }
}
