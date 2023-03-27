package com.zayzou.service;

import com.zayzou.utils.OkHttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TelegramService {

    private final OkHttpUtils okHttpUtils;
    private final GithubService githubService;

    @Value("${telegram.userId}")
    private String userId;
    @Value("${telegram.botId}")
    private String botId;

    public TelegramService(OkHttpUtils okHttpUtils, GithubService githubService) {
        this.okHttpUtils = okHttpUtils;
        this.githubService = githubService;
    }

    public void send(String command) {
        String message = Objects.equals(command, "today") ? githubService.getTodayContribution() : githubService.getCurrentYearContribution();
        String reaction = message.startsWith("No") ? "🙁" : "😍";
        message = String.format("%s %s", reaction, message);
        this.okHttpUtils.httpCall(
                "https://api.telegram.org/bot" + botId + "/sendMessage?chat_id=" + userId + "&text=" + message,
                "POST");
    }
}
