package com.startest.wm.utils.customresponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-30 17:32
 * @描述 用于统一返回给客户端信息实体，统一格式，提高友好度
 **/
@ApiModel("统一返回类型对象")
public class RestResponse<T> {
    @ApiModelProperty("返回码")
    private int code;
    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    public RestResponse() {

    }

    public RestResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponse(int code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
