package org.example.tlias.service.impl;

import jakarta.servlet.http.HttpSession;
import org.example.tlias.mapper.VerificationCodeMapper;
import org.example.tlias.pojo.VerificationCode;
import org.example.tlias.utils.MailUtils;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class VerificationCodeImpl {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Autowired
    private MailUtils mailUtils;
    public Result sendVerificationCode(@RequestParam String email) {
        VerificationCode storedCode = verificationCodeMapper.getVercodeByEmail(email);
        if(storedCode!=null&&LocalDateTime.now().isBefore(storedCode.getExpiresAt())){
            return new Result(21,"重复发验证码",null);
        }
        // 生成6位随机验证码
        String verificationCode = mailUtils.generateVerificationCode();

        // 获取当前时间并加上5分钟
         LocalDateTime expirationTime = LocalDateTime.now();
         expirationTime = expirationTime.plus(5, ChronoUnit.MINUTES);; // 5分钟
        // 发送验证码邮件
        sendEmail(email, verificationCode);
        /*向表中插入该验证码*/
        verificationCodeMapper.insert(new VerificationCode(null, email,verificationCode,expirationTime ,false));
        return new Result(14,"验证码已发送",null);
    }
    public Result verifyCode(String code,String email) {
        VerificationCode verificationCode=verificationCodeMapper.getVercodeByEmail(email);
        if(verificationCode==null){
            new Result(13,"验证码不存在",null);
        }
        // 检查验证码是否存在且未过期
        if (verificationCode != null && verificationCode.getExpiresAt() != null && LocalDateTime.now().isBefore(verificationCode.getExpiresAt())) {
            if (verificationCode.getCode().equals (code)) {
                verificationCodeMapper.deleteVercodeByEmail(email);
                return   new Result(10,"验证码正确",null);
            } else {
                return   new Result(11,"验证码错误",null);
            }
        } else {
            verificationCodeMapper.deleteVercodeByEmail(email);
           return new Result(12,"验证码过期",null);
        }
    }

    private void sendEmail(String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dongchuan0096@163.com");
        message.setTo(to);
        message.setSubject("验证码");
        message.setText("您的验证码是: " + verificationCode);
        mailSender.send(message);
    }
}
