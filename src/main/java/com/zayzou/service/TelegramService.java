package com.zayzou.service;

import org.springframework.stereotype.Service;

@Service
public class TelegramService {

    public void send() {
        System.out.println("send from service");
    }
}
