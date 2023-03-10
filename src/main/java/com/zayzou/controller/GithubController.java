package com.zayzou.controller;

import com.zayzou.service.GithubService;
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

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping
    public String fetch() {
        return githubService.getUpdates();
    }
}
