package com.luobo.eduservice.controller;

import com.luobo.commonutils.Msg;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api("登录控制")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//解决跨域问题
public class EduLoginController {
    //login
    @PostMapping("/login")
    public Msg login(){
        return Msg.sucess().data("token","admin");
    }

    @GetMapping("/info")
    public Msg info(){
        return Msg.sucess().data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //info
}
