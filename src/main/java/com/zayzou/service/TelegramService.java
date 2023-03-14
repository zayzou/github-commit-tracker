package com.zayzou.service;

import com.zayzou.utils.OkHttpUtils;
import org.springframework.stereotype.Service;

@Service
public class TelegramService {

    private final OkHttpUtils okHttpUtils;

    public TelegramService(OkHttpUtils okHttpUtils) {
        this.okHttpUtils = okHttpUtils;
    }

    public void send(String message) {
        String user_id = "5183089051";
        this.okHttpUtils.httpCall(
                "https://api.telegram.org/bot6089177075:AAEPBF7F4RqJOe_crCXccIvENc9JuYxBOBU/sendMessage?chat_id=" + user_id + "&text=" + message,
                "POST");
    }
}
