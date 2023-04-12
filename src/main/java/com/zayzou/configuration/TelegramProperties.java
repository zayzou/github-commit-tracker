/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : TelegramProperties.java
 */

package com.zayzou.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram.bot")
@AllArgsConstructor
@Data
public class TelegramProperties {

    private String token;
    private String username;
}
