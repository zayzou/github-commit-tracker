package com.zayzou.controller;

import com.zayzou.service.EmailService;
import com.zayzou.service.GithubService;
import com.zayzou.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "github", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class GithubController {

    GithubService githubService;
    TelegramService telegramService;
    EmailService emailService;

    public GithubController(GithubService githubService, TelegramService telegramService, EmailService emailService) {
        this.githubService = githubService;
        this.telegramService = telegramService;
        this.emailService = emailService;
    }

    @GetMapping
    public String fetch() {
        String updates = githubService.getCurrentYearContribution();
        telegramService.send("today");
        emailService.send(updates);
        return updates;
    }
}
