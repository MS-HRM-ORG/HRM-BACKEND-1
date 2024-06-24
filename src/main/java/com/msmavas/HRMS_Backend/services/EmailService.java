package com.msmavas.HRMS_Backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailService1 {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage(); 
            message.setFrom("markaakhil00@gmail.com"); // Use the configured Gmail address
            message.setTo(to); 
            message.setSubject(subject); 
            message.setText(text);
            javaMailSender.send(message);
            System.out.println("Email sent successfully to: " + to);
        } catch (MailException e) {
            System.err.println("Error occurred while sending email to: " + to);
            e.printStackTrace();
        }
    }
}
