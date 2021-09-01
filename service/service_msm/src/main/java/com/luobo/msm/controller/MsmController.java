package com.luobo.msm.controller;

import com.luobo.commonutils.Msg;
import com.luobo.msm.service.MsmService;
import com.luobo.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String > redisTemplate;

    @GetMapping("/send/{email}")
    public Msg sendMsg(@PathVariable String email){
        String code = redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)){
            return Msg.success();
        }
        code = RandomUtil.getFourBitRandom();
        boolean isSend = msmService.sendMail(email, code);
        if(isSend){
            redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
        }else {
            return Msg.error().message("发送验证码失败");
        }
        return Msg.success();
    }
}
