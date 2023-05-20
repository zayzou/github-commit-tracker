/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : RootController.java
 */

package com.zayzou.controller;

import com.zayzou.graphql.GithubGraphQLService;
import com.zayzou.telegram.TelegramService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class RootController {
    private final TelegramService telegramService;
    private final GithubGraphQLService githubService;

    public RootController(TelegramService telegramService, GithubGraphQLService githubService) {
        this.telegramService = telegramService;
        this.githubService = githubService;
    }

    @GetMapping("{command}")
    public String execute(@PathVariable String command) {
        String updates = "";
        if (command.equals("year")) {
            updates = githubService.getCurrentYearContributions();
        } else if (command.equals("today")) {
            updates = githubService.getTodayContribution();
        } else {
            updates = "Invalid command :)";
        }
        return updates;

    }

    @GetMapping("/telegram/{command}")
    public String sendToTelegram(@PathVariable String command) {
        String updates = "done";
        if (command.equals("year")) {
            telegramService.sendToTelegram("year");
        } else if (command.equals("today")) {
            telegramService.sendToTelegram("today");
        } else {
            updates = "Invalid command :)";
        }
        return updates;

    }
}
