package com.example;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public static void main(String[] args) {

        String from = System.getenv("EMAIL_USER");
        String password = System.getenv("EMAIL_PASS");
        String to = "receiver@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("CI/CD Email");
            message.setText("Hello! Email from Jenkins + Docker 🚀");

            Transport.send(message);
            System.out.println("Email sent!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}