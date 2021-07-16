package com.luobo.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.luobo.aclservice.entity.Permission;
import com.luobo.aclservice.service.IndexService;
import com.luobo.aclservice.service.PermissionService;
import com.luobo.commonutils.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public Msg info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Msg.sucess().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public Msg getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Msg.sucess().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public Msg logout(){
        return Msg.sucess();
    }

}
