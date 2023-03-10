package com.zayzou.configuration;

import com.zayzou.GithubProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GithubProperties.class)
public class AppConfig {

    public AppConfig() {
    }
}
