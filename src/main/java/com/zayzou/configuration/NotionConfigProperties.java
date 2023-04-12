/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : NotionConfigProperties.java
 */

package com.zayzou.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("notion")
public record NotionConfigProperties
        (String apiUrl, String apiVersion, String authToken, String databaseId) {
}