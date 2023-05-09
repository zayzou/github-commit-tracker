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

        // Create a WebClient that will be used to send GraphQL requests to Github's API.
        // Set the base URL to the GraphQL endpoint.
        // Add default headers that include the Github access token and content type.
        WebClient client = WebClient.builder()
                .baseUrl("https://api.github.com/graphql")
                .defaultHeader("Authorization", "Bearer " + this.githubProperties.getToken())
                .defaultHeader("content-type", "application/json")
                .build();

        // Create a HttpGraphQlClient that will be used to send GraphQL requests
        // to the Github API using the WebClient created earlier.
        graphQlClient = HttpGraphQlClient.builder(client).build();
    }


    public Mono<User> getUser(String username) {
        //language=GraphQL
        String document = """
                query {
                    user(login: "%s") {
                      name
                      login
                      avatarUrl
                    }
                  }
                  """.formatted(username);
        Mono<User> response = graphQlClient.document(document).retrieve("user").toEntity(User.class);
        return response;
    }

    public Mono<UserContribution> getContributionCollections(String username) {
        //language=GraphQL
        String document = """
                query {
                        user(login: "%s") {
                          contributionsCollection(
                            from: "2023-05-01T00:00:00Z"
                            to: "2023-05-31T23:59:00Z"
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
        return graphQlClient.document(document).retrieve("user").toEntity(UserContribution.class);
    }
}
