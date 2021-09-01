package com.luobo.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
//统一结果返回类
@Data
public class Msg {
    @ApiModelProperty(value = "是否成功")
    private boolean success;
    @ApiModelProperty(value = "返回状态码")
    private int code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<>();

    //私有构造器
    private Msg(){}
    //成功返回
    public static Msg success(){
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setCode(ResultCode.SUCCESS);
        msg.setMessage("成功");
        return msg;
    }
    //失败返回
    public static Msg error(){
        Msg msg = new Msg();
        msg.setSuccess(false);
        msg.setCode(ResultCode.ERROR);
        msg.setMessage("失败");
        return msg;
    }

    public Msg success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Msg code(Integer code){
        this.setCode(code);
        return this;
    }

    public Msg data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    public Msg message(String message){
        this.setMessage(message);
        return this;
    }
    public Msg data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
