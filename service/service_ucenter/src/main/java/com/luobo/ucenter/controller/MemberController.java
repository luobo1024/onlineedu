package com.luobo.ucenter.controller;


import com.luobo.commonutils.JwtUtils;
import com.luobo.commonutils.Msg;
import com.luobo.commonutils.ordervo.UcenterMemberOrder;
import com.luobo.ucenter.entity.Member;
import com.luobo.ucenter.entity.vo.RegisterVo;
import com.luobo.ucenter.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {
    @Autowired
    MemberService memberService;
    @PostMapping("/login")
    public Msg loginUser(@RequestBody Member member){
        String token =  memberService.login(member);
        return Msg.success().data("token", token);
    }
    @PostMapping("/register")
    public Msg registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Msg.success();
    }
    @GetMapping("/getMemberInfo")
    public Msg getMemberInfo(HttpServletRequest request){
        //调用jwt工具类方法,根据request对象获取头信息,返回用户ID
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(memberId);
        return Msg.success().data("userInfo",member);

    }
    //根据用户id获取用户信息
    @GetMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
        Member member = memberService.getById(id);
        UcenterMemberOrder memberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,memberOrder);
        return memberOrder;
    }
    

}

