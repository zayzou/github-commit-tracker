package com.zayzou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class GithubCommitTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubCommitTrackerApplication.class, args);
    }

}
