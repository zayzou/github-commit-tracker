/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : DailyJob.java
 */

package com.zayzou.utils;

import com.zayzou.telegram.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class DailyJob {


    final
    TelegramService telegramService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public DailyJob(TelegramService telegramService) {
        this.telegramService = telegramService;

    }

    @Scheduled(cron = "0 0 20 * * ?", zone = "GMT+1") //to run every day at 8pm we will use "0 0 20 * * ?"
    public void run() {
        log.info("The time is {}", dateFormat.format(new Date()));
        telegramService.sendToTelegram("today");
    }
}
