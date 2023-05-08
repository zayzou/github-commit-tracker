/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : User.java
 */

package com.zayzou.graphql;

import java.util.List;

public record User(
        String name,
        String login,
        String avatarUrl,
        ContributionCollection contributionsCollection

) {
}

record ContributionCollection(List<Integer> years) {
}


record GitHubApiResponse(Data data) {
}

record Data(UserContrib user) {
}

record UserContrib(ContributionsCollection contributionsCollection) {
}

record ContributionsCollection(ContributionCalendar contributionCalendar) {
}

record ContributionCalendar(int total, List<Week> weeks) {
}

record Week(List<Day> days) {
}

record Day(String date, int contributionCount) {
}

