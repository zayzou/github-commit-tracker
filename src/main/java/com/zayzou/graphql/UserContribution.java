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

    public ContributionsCollection getContributionsCollection() {
        return contributionsCollection;
    }

    public void setContributionsCollection(ContributionsCollection contributionsCollection) {
        this.contributionsCollection = contributionsCollection;
    }
}

class ContributionsCollection {
    private ContributionCalendar contributionCalendar;

    public ContributionCalendar getContributionCalendar() {
        return contributionCalendar;
    }

    public void setContributionCalendar(ContributionCalendar contributionCalendar) {
        this.contributionCalendar = contributionCalendar;
    }
}

class ContributionCalendar {
    private int total;
    private List<Week> weeks;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }
}

class Week {

    private List<Day> days;

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}

class Day {
    private String date;
    private int contributionCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getContributionCount() {
        return contributionCount;
    }

    public void setContributionCount(int contributionCount) {
        this.contributionCount = contributionCount;
    }
}
