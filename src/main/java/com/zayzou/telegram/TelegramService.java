/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : TelegramService.java
 */

package com.zayzou.telegram;

import com.zayzou.graphql.GithubGraphQLService;
import com.zayzou.utils.OkHttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TelegramService {

    private final OkHttpUtils okHttpUtils; // OkHttpUtils instance for making HTTP calls
    private final GithubGraphQLService githubService; // GithubService instance for fetching Github contributions data

    @Value("${telegram.userId}")
    private String userId; // Telegram user ID injected from properties file
    @Value("${telegram.botId}")
    private String botId; // Telegram bot ID injected from properties file

    public TelegramService(OkHttpUtils okHttpUtils, GithubGraphQLService githubService) {
        this.okHttpUtils = okHttpUtils;
        this.githubService = githubService;
    }

    public void sendToTelegram(String command) {
        String message = getResult(command);
        String url = "https://api.telegram.org/bot" + botId + "/sendMessage?chat_id=" + userId + "&text=" + message;
        this.okHttpUtils.httpCall(url, "POST");
    }


    public String getResult(String command) {
        String message = Objects.equals(command, "today") ? githubService.getTodayContribution() : githubService.getCurrentYearContributions(); // Fetch Github contributions based on command ("today" or "year")
        String reaction = message.startsWith("No") ? "😭" : "🥳"; // Set reaction emoji based on Github contributions message
        message = String.format("%s %s", reaction, message); // Format the final message with reaction emoji
        return message;

    }
}
