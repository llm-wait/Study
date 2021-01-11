package com.startest.wm.utils.customresponse;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-30 16:11
 * @描述 响应工具类
 **/
public class RestResponseUtil {

    /**
     * @Description: 成功返回成功实体, 默认状态码和默认消息，自定义数据
     * @Param: [data]返回数据
     * @return: com.startest.wm.utils.RestResponse<T>
     **/
    public static <T> RestResponse<T> success(T data) {
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setCode(RestResponseCode.SUCCESS.getCode());
        restResponse.setMessage(RestResponseCode.SUCCESS.getMsg());
        restResponse.setData(data);
        return restResponse;
    }

    /**
     * @Description: 成功返回成功实体, 默认状态码和默认状态码消息，无数据返回
     * @Param: []
     * @return: com.startest.wm.utils.RestResponse<java.lang.Object>
     **/
    public static <T> RestResponse<T> success() {
        return success(null);
    }

    /**
     * @Description: 成功返回成功实体, 默认状态码和自定义返回消息，无数据返回
     * @Param: []
     * @return: com.startest.wm.utils.RestResponse<java.lang.Object>
     **/
    public static <T> RestResponse<T> success(String msg) {
        return success(msg, null);
    }

    /**
     * @Description: 成功返回成功实体, 默认状态码和自定义消息，自定义数据
     * @Param: [msg, data]返回消息，返回数据
     * @return: com.startest.wm.utils.RestResponse<T>
     **/
    public static <T> RestResponse<T> success(String msg, T data) {
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setCode(RestResponseCode.SUCCESS.getCode());
        restResponse.setMessage(msg);
        restResponse.setData(data);
        return restResponse;
    }

    /**
     * @Description: 创建一个响应实体对象，返回默认状态码消息和自定义数据
     * @Param: [restResponseCode, data] 返回数据
     * @return: com.startest.wm.utils.RestResponse<T>
     **/
    public static <T> RestResponse<T> createResponse(RestResponseCode restResponseCode, T data) {
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setCode(restResponseCode.getCode());
        restResponse.setMessage(restResponseCode.getMsg());
        restResponse.setData(data);
        return restResponse;
    }

    /**
     * @Description: 创建一个响应实体对象，返回响应状态码，自定义消息和自定义数据
     * @Param: [restResponseCode, msg, data]响应状态码，自定义消息，返回数据
     * @return: com.startest.wm.utils.customresponse.RestResponse<T>
     **/
    public static <T> RestResponse<T> createResponse(RestResponseCode restResponseCode, String msg, T data) {
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setCode(restResponseCode.getCode());
        restResponse.setMessage(msg);
        restResponse.setData(data);
        return restResponse;
    }

    /**
     * @Description: 创建一个响应实体对象，返回响应状态码和默认状态码消息
     * @Param: [restResponseCode] 返回数据
     * @return: com.startest.wm.utils.RestResponse<T>
     **/
    public static <T> RestResponse<T> createResponse(RestResponseCode restResponseCode) {
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setCode(restResponseCode.getCode());
        restResponse.setMessage(restResponseCode.getMsg());
        return restResponse;
    }

    /**
     * @Description: 系统提示
     * @Param: [msg] 提示信息
     * @return: com.startest.wm.utils.customresponse.RestResponse<T>
     **/
    public static <T> RestResponse<T> note(String msg) {
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setCode(RestResponseCode.NOTE.getCode());
        restResponse.setMessage(msg);
        return restResponse;
    }

    /**
     * @Description: 系统提示
     * @Param: [msg, data]
     * @return: com.startest.wm.utils.customresponse.RestResponse<T>
     **/
    public static <T> RestResponse<T> note(String msg, T data) {
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setCode(RestResponseCode.NOTE.getCode());
        restResponse.setMessage(msg);
        restResponse.setData(data);
        return restResponse;
    }

    /**
     * @Description: 错误返回错误信息实体
     * @Param: [code, msg]错误码，错误信息
     * @return: com.startest.wm.utils.RestResponse<T>
     **/
    public static <T> RestResponse<T> error(int code, String msg) {
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setCode(code);
        restResponse.setMessage(msg);
        return restResponse;
    }
}
