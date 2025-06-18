package org.example.tlias.controller;



import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.tlias.service.impl.VerificationCodeImpl;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class VerificationCodeController {


    @Autowired
    private VerificationCodeImpl verificationCode;
    @PostMapping("/send-verification-code")
    public Result sendVerificationCode( @RequestParam String email) {
        return verificationCode.sendVerificationCode(email);
    }

    @PostMapping("/verify-code")
    public Result verifyCode(@RequestParam String code,@RequestParam String email) {
        return  verificationCode.verifyCode(code,email);
    }


}
