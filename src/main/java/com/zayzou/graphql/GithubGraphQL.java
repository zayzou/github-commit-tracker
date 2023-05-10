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

import java.util.List;

@RestController
@RequestMapping("graphql")
public class GithubGraphQL {


    private final GithubGraphQLService githubGraphQLService;


    public GithubGraphQL(GithubGraphQLService githubGraphQLService) {
        this.githubGraphQLService = githubGraphQLService;
    }

    @GetMapping("/user/{username}")
    public void ping(@PathVariable String username) {
        Mono<User> user = this.githubGraphQLService.getUser(username);
        user.subscribe(response -> System.out.println(response));
    }


    @GetMapping("/contributions/{username}")
    public List<Week> all(@PathVariable String username) {

        Mono<UserContribution> contributionCollections =
                this.githubGraphQLService.getContributionCollections
                        (username, "2023-05-01T00:00:00Z", "2023-05-31T23:59:00Z");
        List<Week> weeks = contributionCollections
                .block()
                .getContributionsCollection()
                .getContributionCalendar()
                .getWeeks();
        return weeks;


    }


}