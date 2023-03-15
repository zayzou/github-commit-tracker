package com.zayzou.service;

import com.zayzou.utils.OkHttpUtils;
import org.springframework.stereotype.Service;

@Service
public class TelegramService {

    private final OkHttpUtils okHttpUtils;
    private final GithubService githubService;

    public TelegramService(OkHttpUtils okHttpUtils, GithubService githubService) {
        this.okHttpUtils = okHttpUtils;
        this.githubService = githubService;
    }

    public void send() {
        String user_id = "5183089051";
        String message = githubService.getUpdates();
        String reaction = message.startsWith("No") ? "🙁" : "😍";
        message = String.format("%s %s", message, reaction);
        this.okHttpUtils.httpCall(
                "https://api.telegram.org/bot6089177075:AAEPBF7F4RqJOe_crCXccIvENc9JuYxBOBU/sendMessage?chat_id=" + user_id + "&text=" + message,
                "POST");
    }
}
