/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : DemoController.java
 * This class is just for tests purpose
 */

package com.zayzou.controller;

import com.zayzou.github.GithubProperties;
import com.zayzou.telegram.TelegramService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo", produces = "applciation/json")
public class DemoController {


    private final GithubProperties githubProperties;

    private final TelegramService telegramService;

    public DemoController(GithubProperties githubProperties, TelegramService telegramService) {
        this.githubProperties = githubProperties;
        this.telegramService = telegramService;
    }

    @GetMapping(params = "telegram")
    public String telegram() {
        telegramService.sendToTelegram("today");
        return "telegram message sent";
    }


}
