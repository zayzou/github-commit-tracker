/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : TelegramService.java
 */

package com.zayzou.service;

import com.zayzou.utils.OkHttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TelegramService {

    private final OkHttpUtils okHttpUtils; // OkHttpUtils instance for making HTTP calls
    private final GithubService githubService; // GithubService instance for fetching Github contributions data

    @Value("${telegram.userId}")
    private String userId; // Telegram user ID injected from properties file
    @Value("${telegram.botId}")
    private String botId; // Telegram bot ID injected from properties file

    public TelegramService(OkHttpUtils okHttpUtils, GithubService githubService) {
        this.okHttpUtils = okHttpUtils;
        this.githubService = githubService;
    }

    public void send(String command) {
        String message = Objects.equals(command, "today") ? githubService.getTodayContribution() : githubService.getCurrentYearContribution(); // Fetch Github contributions based on command ("today" or "year")
        String reaction = message.startsWith("No") ? "😭" : "🥳"; // Set reaction emoji based on Github contributions message
        message = String.format("%s %s", reaction, message); // Format the final message with reaction emoji
        this.okHttpUtils.httpCall(
                "https://api.telegram.org/bot" + botId + "/sendMessage?chat_id=" + userId + "&text=" + message, // Construct Telegram API URL with bot ID, user ID, and message
                "POST"); // Make HTTP POST call to send the message to Telegram
    }
}
