/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : AppConfig.java
 */

package com.zayzou.configuration;

import com.zayzou.github.GithubProperties;
import com.zayzou.telegram.TelegramBot;
import com.zayzou.telegram.TelegramProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Configuration
@EnableConfigurationProperties({GithubProperties.class, TelegramProperties.class, NotionConfigProperties.class})
public class AppConfig {

    @Autowired
    TelegramBot telegramBot;

    @Bean
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
