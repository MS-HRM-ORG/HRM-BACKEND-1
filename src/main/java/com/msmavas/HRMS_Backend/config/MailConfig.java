package com.msmavas.HRMS_Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import jakarta.mail.Session;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Update SMTP server to Gmail
        mailSender.setPort(587); // Gmail SMTP port
        mailSender.setUsername("markaakhil00@gmail.com"); // Your Gmail username
        mailSender.setPassword("aqrb sntu zvpm xhjb"); // Your Gmail password (Consider using environment variables or secure vaults for sensitive data)

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props);
        session.setDebug(true); // Enable debugging

        TrustManager[] trustManagers = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, null);
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            props.put("mail.smtp.ssl.socketFactory", socketFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mailSender;
    }
}
