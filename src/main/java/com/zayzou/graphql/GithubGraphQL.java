/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : Controller.java
 */

package com.zayzou.graphql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("graphql")
public class GithubGraphQL {


    private final String GITHUB_TOKEN = "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";
    private GithubGraphQLService githubGraphQLService;

    public GithubGraphQL(GithubGraphQLService githubGraphQLService) {
        this.githubGraphQLService = githubGraphQLService;
    }

    @GetMapping("/ping")
    public void ping() {
        Mono<User> user = this.githubGraphQLService.getUser();
        user.subscribe(response -> System.out.println(response));
    }

}