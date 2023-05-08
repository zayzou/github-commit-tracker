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
        Mono<User> response = graphQlClient.document(document).retrieve("user").toEntity(User.class);
        return response;
    }

    public Mono<String> getContributions(String username) {
        String document = """
                {
                  user(login: "%s") {
                    contributionsCollection(
                      from: "2023-01-01T00:00:00Z"
                      to: "2023-05-08T23:59:00Z"
                    ) {
                      contributionCalendar {
                        total: totalContributions
                        weeks {
                          days: contributionDays {
                            date
                            contributionCount
                          }
                        }
                      }
                    }
                  }
                }
                """.formatted(username);
        return null;
    }

}
