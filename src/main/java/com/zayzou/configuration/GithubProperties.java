/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : GithubProperties.java
 */

package com.zayzou.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "github")
@AllArgsConstructor
@Data
public class GithubProperties {

    private String token;
    private String username;
}
