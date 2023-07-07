package com.fragile.cafe_backend.utils
        ;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailUtils {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username")
    private String sender;


    public void sendSimpleMail(String to, String subject, String text, List<String> receivers) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setText(text);
        message.setSubject(subject);
        message.setTo(to);


        if (receivers.size() > 0)
            message.setCc(getCcArray(receivers));
        javaMailSender.send(message);
    }

    public String[] getCcArray(List<String> receivers) {
        String[] cc = new String[receivers.size()];
        for (int i = 0; i < receivers.size(); i++) {
            cc[i] = receivers.get(i);
        }
        return cc;
    }

    public void passwordRecoveryMail(String to, String subject, String password) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        String htmlMsg = "<p><b>Your Login details for Fragile Cafe Management System</b><br><b>Email: </b> " + to + " <br><b>Password: </b> " + password + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>";
        mimeMessage.setContent(htmlMsg, "text/html");
        javaMailSender.send(mimeMessage);
    }
}
