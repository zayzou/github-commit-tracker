/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : GithubService.java
 */

package com.zayzou.graphql;

import com.zayzou.configuration.GithubProperties;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GithubGraphQLService {


    private final HttpGraphQlClient graphQlClient;
    private GithubProperties githubProperties;

    public GithubGraphQLService(GithubProperties githubProperties) {
        this.githubProperties = githubProperties;
        WebClient client = WebClient.builder()
                .baseUrl("https://api.github.com/graphql")
                .defaultHeader("Authorization", "Bearer " + this.githubProperties.getToken())
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

    public Mono<UserContrib> getContributionCollections(String username) {
        //language=GraphQL
        String document = """
                query {
                    user(login: "zayzou") {
                      contributionsCollection(
                        from: "2023-05-01T00:00:00Z"
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
                 """;
        //Mono<UserContrib> response =
        graphQlClient.document(document)
                .retrieve("user").toEntity(UserContrib.class).subscribe(System.out::println);


        return null;

    }

}
