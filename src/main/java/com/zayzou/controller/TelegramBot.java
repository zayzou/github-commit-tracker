/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : TelegramBot.java
 */

package com.zayzou.controller;

import com.zayzou.service.TelegramService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    TelegramService telegramService;

    @Value("${telegram.bot.username}")
    private String username;

    @Value(("${telegram.bot.token}"))
    private String token;

    public TelegramBot(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @NotNull
    private static String getAbout() {
        return """
                👋 Hello, I am the 🤖 SpringyContributionBot 🌱, your friendly assistant for GitHub contributions! 🎉\s
                Use /today to get the number of contributions you made today! 📈\s
                Use /year to get the total number of contributions you made this year! 📊\s
                Keep calm and code on! 💻🚀
                """;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            String value = switch (message) {
                case "/about" -> getAbout(); // If message is "/about", call getAbout() method
                case "/year" -> send("year"); // If message is "/year", call send() method with "year" command
                case "/today" -> send("today"); // If message is "/today", call send() method with "today" command
                case "/test" -> "Testing the bot"; // If message is "/test", return "Testing the bot"
                default -> " "; // For any other message, return empty string
            };
            SendMessage sendMessage = new SendMessage(); // Create a SendMessage object with mandatory fields
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText(value);
            try {
                execute(sendMessage); // Call method to send the sendMessage
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to send commands to TelegramService
    private String send(String command) {
        return telegramService.getResult(command);
    }

    @Override
    public String getBotUsername() {
        return this.username; // Return the bot's username
    }

    @Override
    public String getBotToken() {
        return this.token; // Return the bot's token
    }
}
