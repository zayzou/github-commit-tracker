package com.zayzou.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class BotController extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            String value = switch (message) {
                case "/about" -> "this is a bot that send my daily github contribution";
                case "/total" -> "The total commits is ";
                case "/run" -> "running the process";
                default -> message;
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

    @Override
    public String getBotUsername() {
        return "messagingtracker_bot";
    }

    @Override
    public String getBotToken() {
        return "6089177075:AAEPBF7F4RqJOe_crCXccIvENc9JuYxBOBU";
    }


}