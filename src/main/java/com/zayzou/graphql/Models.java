/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : User.java
 */

package com.zayzou.graphql;

import java.util.List;


record GitHubUser(String name, GitHubUsername login, String avatarUrl,
                  ContributionYears contributionsCollection) {
}

record GitHubContributionCalendar(ContributionsCollection contributionsCollection) {
}

record ContributionBasic(String name, GitHubUsername login, String avatarUrl,
                         ContributionYears contributionYears) {
}

record ContributionYears(List<ContributionYear> years) {
}

record ContributionYear(int year, int totalContributions, List<ContributionWeek> contributionWeeks) {
}

record ContributionWeek(List<ContributionDay> contributionDays) {
}

record ContributionDay(String date, int contributionCount) {
}

record ContributionsCollection(ContributionCalendar contributionCalendar) {
}

record ContributionCalendar(int totalContributions, List<ContributionWeek> weeks) {
}

record GitHubUsername(String login) {
}
