package com.zayzou.controller;

import com.zayzou.GithubProperties;
import com.zayzou.service.EmailService;
import com.zayzou.service.TelegramService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo", produces = "applciation/json")
public class DemoController {


    private final GithubProperties githubProperties;
    private final EmailService emailService;
    private final TelegramService telegramService;

    public DemoController(GithubProperties githubProperties, EmailService emailService, TelegramService telegramService) {
        this.githubProperties = githubProperties;
        this.emailService = emailService;
        this.telegramService = telegramService;
    }

    @GetMapping(params = "telegram")
    public String telegram() {
        telegramService.send("message");
        return "telegram message sent";
    }

    @GetMapping(params = "email")
    public String email() {
        emailService.send("text");
        return "done";

    }



}
