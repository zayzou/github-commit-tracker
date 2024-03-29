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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.zayzou.utils.DateFormatter.getFormattedDate;

@Service
public class GithubGraphQLService {


    private final HttpGraphQlClient graphQlClient;
    private final GithubProperties githubProperties;

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

    public Mono<UserContribution> getContributionCollections(String username, String from, String to) {
        //language=GraphQL
        String document = """
                query {
                        user(login: "%s") {
                          contributionsCollection(
                            from: "%s"
                            to: "%s"
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
                 """.formatted(username, from, to);
        return graphQlClient.document(document).retrieve("user").toEntity(UserContribution.class);
    }


    public String getTodayContribution() {
        String today = getFormattedDate(LocalDateTime.now());
        List<Week> weeks = this.getContributionCollections(githubProperties.getUsername(), today, today)
                .block()
                .getContributionsCollection()
                .getContributionCalendar()
                .getWeeks();

        int contributionCount = weeks.get(0).getDays().get(0).getContributionCount();
        String date = weeks.get(0).getDays().get(0).getDate();
        String template = "%s contributions on %s, ".formatted(contributionCount, date);
        return template;

    }

    public String getCurrentYearContributions() {
        String firstDayOfYear = getFormattedDate(LocalDateTime.now().withDayOfYear(1));
        String today = getFormattedDate(LocalDateTime.now());
        int total = getContributionCollections(githubProperties.getUsername(), firstDayOfYear, today)
                .block()
                .getContributionsCollection()
                .getContributionCalendar()
                .getTotal();
        String template = "%s contributions in %s".formatted(total, LocalDate.now().getYear());
        return template;

    }
}
