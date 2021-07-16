package com.luobo.servicebase.exceptionhandler;


import com.luobo.commonutils.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

//统一异常处理类
@ControllerAdvice
@Slf4j//输出异常到文件
public class GlobalExceptionHandler {

    //指定出现什么异常开始执行
 //   @ExceptionHandler(Exception.class)
    @ResponseBody
    public Msg error(){
        return Msg.error().message("执行了全局异常处理");
    }
}
