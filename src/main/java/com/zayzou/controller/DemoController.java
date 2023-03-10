package com.zayzou.controller;

import com.zayzou.GithubProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo", produces = "applciation/json")
public class DemoController {


    private final GithubProperties githubProperties;

    public DemoController(GithubProperties githubProperties) {
        this.githubProperties = githubProperties;
    }


    @GetMapping
    public String demo() {
        System.out.println(githubProperties.getToken());
        return githubProperties.getToken();

    }
}
