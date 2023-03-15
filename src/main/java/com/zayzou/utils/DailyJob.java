package com.zayzou.utils;

import com.zayzou.service.GithubService;
import com.zayzou.service.TelegramService;
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
    GithubService githubService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public DailyJob(TelegramService telegramService, GithubService githubService) {
        this.telegramService = telegramService;
        this.githubService = githubService;
    }

    @Scheduled(cron = "0 * * * * *", zone = "UTC")
    public void run() {
        log.info("The time is {}", dateFormat.format(new Date()));
        telegramService.send(this.githubService.getUpdates());
    }
}
