package com.utkicode.Sendingemail.controller;

import com.utkicode.Sendingemail.Entity.EmailRequest;
import com.utkicode.Sendingemail.Entity.User;
import com.utkicode.Sendingemail.Repository.UserRepository;
import com.utkicode.Sendingemail.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailCotroller {
    @Autowired
    EmailService emailService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmailToUsers(@RequestBody EmailRequest emailRequest)
    {
        List<User> users = userRepository.findAll();
        try {
            emailService.sendEmailsToUser(users, emailRequest.getSubject(), emailRequest.getBody());
            return ResponseEntity.status(HttpStatus.OK).body("Email Sent Successfully");
        }catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service is not Available");
        }


    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(
            @RequestParam String toEmail,
            @RequestParam String subject,
            @RequestParam String body) {
        try {
            emailService.sendMailByUser(toEmail,subject,body);
            return ResponseEntity.status(HttpStatus.OK).body("Email sent to " + toEmail);
        }catch (Exception exception)
        {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error!");
        }

    }


}
