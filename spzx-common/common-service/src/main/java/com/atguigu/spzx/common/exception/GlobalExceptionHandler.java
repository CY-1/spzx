package com.atguigu.spzx.common.exception;



import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GuiguException.class)     // 处理自定义异常
    public Result guiguExceptionHandler(GuiguException exception) {
        exception.printStackTrace();
        return Result.build(null , exception.getResultCodeEnum()) ;
    }

    @ExceptionHandler(value = Exception.class)          // 处理系统异常
    public Result systemExceptionHandler(Exception exception) {
        exception.printStackTrace();
        return Result.build(null , ResultCodeEnum.SYSTEM_ERROR) ;
    }

}