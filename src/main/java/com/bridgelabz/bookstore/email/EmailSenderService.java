package com.bridgelabz.bookstore.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderService {

    @Autowired
    public JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom("prashant1424325@gmail.com");
        msg.setTo(toEmail);
        msg.setText(body);
        msg.setSubject(subject);

        mailSender.send(msg);
        System.out.println("Mail sent to the user...");
    }
}
