/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : EmailService.java
 */

package com.zayzou.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo("soffizahir2016@gmail.com");
            helper.setSubject("🍃Github Contribution Tracker 🚀");
            helper.setText(text, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
