package org.example.tlias.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import java.util.Random;
@Component
public  class MailUtils  {
    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

}