package com.ls.usercenter.Exception;

import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.ls.usercenter.common.BaseResponse;
import com.ls.usercenter.common.ErrorCode;
import com.ls.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException e) {
        log.error("businessException" + e.getMessage(),e);
        return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription()) ;
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(BusinessException e) {
        log.error("RuntimeException" ,e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"") ;
    }
}
