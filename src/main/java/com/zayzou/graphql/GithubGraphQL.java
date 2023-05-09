/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : Controller.java
 */

package com.zayzou.graphql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/user/{username}")
    public void ping(@PathVariable String username) {
        Mono<User> user = this.githubGraphQLService.getUser(username);
        user.subscribe(response -> System.out.println(response));
    }

    @GetMapping("/contributions/{username}")
    public void all(@PathVariable String username) {
        Mono<UserContribution> user = this.githubGraphQLService.getContributionCollections(username);
        user.subscribe(
                response -> System.out.println("Total contribution : " + response.getContributionsCollection().getContributionCalendar().getTotal()
                ));
    }

}