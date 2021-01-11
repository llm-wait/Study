package com.startest.wm.config.exceptionHandler;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-03-27-10:34
 * @描述 常见错误类型枚举
 */
public enum CommonErrorEnum implements BaseErrorInfoInterface {
    //数据操作错误定义
    SUCCESS("200","成功!"),
    ERROR("300","失败!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404","未找到该资源!"),
    INTERNAL_SERVER_ERROR("500","服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!")
    ;

    /*
    错误码
     */
    private  String resultCode;

    /*
    错误描述
     */
    private String resultMsg;

    CommonErrorEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
