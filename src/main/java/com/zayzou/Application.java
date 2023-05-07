/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : GithubCommitTrackerApplication.java
 */

package com.zayzou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
