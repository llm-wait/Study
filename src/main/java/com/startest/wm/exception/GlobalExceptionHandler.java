package com.startest.wm.exception;

import com.startest.wm.config.exception.LoginException;
import com.startest.wm.utils.customresponse.RestResponse;
import com.startest.wm.utils.customresponse.RestResponseCode;
import com.startest.wm.utils.customresponse.RestResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.Objects;

/**
 * @author Administrator
 * @date 2020/11/2 0002 11:02
 * @PackageName:StartestWM
 * @ClassName:自定义统一异常处理器 描述： Validation参数校验
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    protected static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public RestResponse<Object> handle(Exception e) {
        log.error(e.toString());
        if (e instanceof BindException) {
            BindException exs = (BindException) e;
            return RestResponseUtil.createResponse(RestResponseCode.PARAMETERERROR, exs.getMessage());
        }
        if (e instanceof ValidationException) {
            ValidationException exs = (ValidationException) e;
            return RestResponseUtil.createResponse(RestResponseCode.PARAMETERERROR, exs.getMessage());
        }
        if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException exs = (MissingServletRequestParameterException) e;
            return RestResponseUtil.createResponse(RestResponseCode.PARAMETERERROR, exs.getMessage());
        }
        if (e instanceof LoginException) {
            return RestResponseUtil.note("登陆超时");
        }
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exs = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = exs.getBindingResult();
            return RestResponseUtil.createResponse(RestResponseCode.PARAMETERERROR, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return RestResponseUtil.createResponse(RestResponseCode.SERVER_BUSY, e.getMessage());
    }
}
