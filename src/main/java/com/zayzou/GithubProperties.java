package com.zayzou;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "github")
@AllArgsConstructor
@Data
public class GithubProperties {

    private String token;
}
