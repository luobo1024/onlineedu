package com.luobo.msm.service.impl;

import com.luobo.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    JavaMailSenderImpl mailSender;

    private String emailServiceCode;
    @Override
    public boolean sendMail(String email, String code) {
        emailServiceCode=code;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");
        message.setText("你的验证码是" + emailServiceCode);
        message.setFrom("616433374@qq.com");
        message.setTo(email);
        mailSender.send(message);
        return true;
    }
}
