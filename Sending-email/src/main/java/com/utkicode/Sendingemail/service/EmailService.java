package com.utkicode.Sendingemail.service;

import com.utkicode.Sendingemail.Entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    public void sendEmailsToUser(List<User> userList, String subject, String body)
    {
        for(User user : userList)
        {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            try{
                mimeMessageHelper.setTo(user.getEmail());
                mimeMessageHelper.setTo(subject);
                mimeMessageHelper.setTo(body);
            }catch ( MessagingException messagingException)
            {
                messagingException.printStackTrace();
            }
        }

    }
}
