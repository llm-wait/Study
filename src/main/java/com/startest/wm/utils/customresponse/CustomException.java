package com.startest.wm.utils.customresponse;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-30 17:12
 * @描述 自定义异常
 **/
public class CustomException extends  Exception{
    private static final long serialVersionUID=638627862486727627L;
    private int code;

    public CustomException(RestResponseCode restResponseCode){
        super(restResponseCode.getMsg());
        this.code=restResponseCode.getCode();
    }

    public CustomException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
