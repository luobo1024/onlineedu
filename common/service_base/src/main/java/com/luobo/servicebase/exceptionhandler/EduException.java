package com.luobo.servicebase.exceptionhandler;

import com.luobo.commonutils.Msg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ControllerAdvice

@Slf4j
public class EduException extends RuntimeException{
    private  Integer code;
    private String msg;

    //自定义异常
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public Msg error(EduException e){
        return Msg.error().code(e.getCode()).message(e.getMsg());
    }

}
