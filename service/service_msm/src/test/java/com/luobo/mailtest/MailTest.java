package com.luobo.mailtest;

import com.luobo.msm.MsmApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MsmApplication.class})
public class MailTest {

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    JavaMailSenderImpl mailSender;
    private String emailServiceCode;
    @Test
    public void run(){
        emailServiceCode="1234";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");
        message.setText("你的验证码是" + emailServiceCode);
        message.setFrom("616433374@qq.com");
        message.setTo("2632153351@qq.com");
        mailSender.send(message);



       /* MailThread mailThread = new MailThread();

        Thread t1 = new Thread(mailThread);
        t1.start();
       // mailSender.send(message);*/
    }
    /*class MailThread implements Runnable{
        @Override
        public void run() {

           // mailSender.send(message);
        }
    }*/
}
