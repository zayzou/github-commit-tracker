package com.zayzou.controller;

import com.zayzou.GithubProperties;
import com.zayzou.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo", produces = "applciation/json")
public class DemoController {


    private final GithubProperties githubProperties;
    private final EmailService emailService;

    public DemoController(GithubProperties githubProperties, EmailService emailService) {
        this.githubProperties = githubProperties;
        this.emailService = emailService;
    }


    @GetMapping
    public String demo() {
        emailService.send();
        return "done";

    }
}
