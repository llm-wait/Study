package com.startest.wm.utils.customresponse;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-30 16:08
 * @描述 响应码枚举
 **/
public enum RestResponseCode {
    SUCCESS(200, "success"),
    FAIL(300, "fail"),
    BODY_NOT_MATCH(400, "请求的数据格式不符！"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配！"),
    NOT_FOUND(404, "未找到该资源！"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误！"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试！"),
    UNDEFINED_ERROR(1000, "未知错误！"),
    NOTE(1001, "自定义提示！"),
    PARAMETERERROR(409, "参数错误！"),
    NOTLOGIN(402, "登陆错误！"),
    EXPIRE(1002,"Session过期了");

    int code;
    String msg;

    RestResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
