/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : GithubService.java
 */

package com.zayzou.graphql;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GithubGraphQLService {


    private final HttpGraphQlClient graphQlClient;

    public GithubGraphQLService() {
        WebClient client = WebClient.builder()
                .baseUrl("https://api.github.com/graphql")
                .defaultHeader("Authorization", "Bearer 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")
                .defaultHeader("content-type", "application/json")
                .build();

        graphQlClient = HttpGraphQlClient.builder(client).build();
    }


    public Mono<User> getUser() {
        //language=GraphQL
        String document = """
                query {
                   user(login: "zayzou") {
                     name
                     login
                     avatarUrl
                     contributionsCollection {
                           years: contributionYears
                         }
                   }
                 }
                           """;
        Mono<User> viewers = graphQlClient.document(document).retrieve("user").toEntity(User.class);
        return viewers;
    }

}
