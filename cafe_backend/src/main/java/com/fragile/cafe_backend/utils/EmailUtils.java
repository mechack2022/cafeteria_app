package com.fragile.cafe_backend.utils
        ;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailUtils {

    private final JavaMailSender javaMailSender;

    public void sendSimpleMail(String to, String subject, String text, List<String> receivers) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("taiwogboyegun@gmail.com");
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
}
