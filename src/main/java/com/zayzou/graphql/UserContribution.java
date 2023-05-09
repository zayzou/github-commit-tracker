/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : User.java
 */

package com.zayzou.graphql;

import java.util.List;

public class UserContribution {
    private ContributionsCollection contributionsCollection;

    ContributionsCollection getContributionsCollection() {
        return contributionsCollection;
    }

    void setContributionsCollection(ContributionsCollection contributionsCollection) {
        this.contributionsCollection = contributionsCollection;
    }
}

class ContributionsCollection {
    private ContributionCalendar contributionCalendar;

    ContributionCalendar getContributionCalendar() {
        return contributionCalendar;
    }

    void setContributionCalendar(ContributionCalendar contributionCalendar) {
        this.contributionCalendar = contributionCalendar;
    }
}

class ContributionCalendar {
    private int total;
    private List<Week> weeks;

    int getTotal() {
        return total;
    }

    void setTotal(int total) {
        this.total = total;
    }

    List<Week> getWeeks() {
        return weeks;
    }

    void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }
}

class Week {
    private List<Day> days;

    List<Day> getDays() {
        return days;
    }

    void setDays(List<Day> days) {
        this.days = days;
    }
}

class Day {
    private String date;
    private int contributionCount;

    String getDate() {
        return date;
    }

    void setDate(String date) {
        this.date = date;
    }

    int getContributionCount() {
        return contributionCount;
    }

    void setContributionCount(int contributionCount) {
        this.contributionCount = contributionCount;
    }
}
