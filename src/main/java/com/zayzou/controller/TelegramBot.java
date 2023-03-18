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
                case "/about" -> getAbout();
                case "/year" -> send("year");
                case "/today" -> send("today");
                case "/test" -> "Testing the bot";
                default -> " ";
            };
            System.out.println(value);
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


    private String send(String command) {
        telegramService.send(command);
        return " ";
    }


    @Override
    public String getBotUsername() {
        return this.username;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }


}