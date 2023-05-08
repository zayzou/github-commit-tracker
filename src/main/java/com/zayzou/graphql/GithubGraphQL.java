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


    private GithubGraphQLService githubGraphQLService;

    public GithubGraphQL(GithubGraphQLService githubGraphQLService) {
        this.githubGraphQLService = githubGraphQLService;
    }

    @GetMapping("/ping")
    public void ping() {
        Mono<GitHubApiResponse> user = this.githubGraphQLService.getContributionCollections("zayzou");
        user.subscribe(response -> System.out.println(response));
    }

}