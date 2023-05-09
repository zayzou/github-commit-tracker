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

