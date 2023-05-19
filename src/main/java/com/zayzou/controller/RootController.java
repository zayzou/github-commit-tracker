/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : RootController.java
 */

package com.zayzou.controller;


import com.zayzou.github.GithubService;
import com.zayzou.telegram.TelegramService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class RootController {
    private final TelegramService telegramService;
    private final GithubService githubService;

    public RootController(TelegramService telegramService, GithubService githubService) {
        this.telegramService = telegramService;
        this.githubService = githubService;
    }

    @GetMapping("{command}")
    public String execute(@PathVariable String command) {
        String updates = "";
        if (command.equals("year")) {
            updates = githubService.getCurrentYearContribution();
        } else if (command.equals("today")) {
            updates = githubService.getTodayContribution();
        } else {
            updates = "Invalid command :)";
        }
        return updates;

    }
}
