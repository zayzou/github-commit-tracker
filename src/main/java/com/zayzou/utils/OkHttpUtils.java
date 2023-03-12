package com.zayzou.utils;

import com.zayzou.GithubProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class OkHttpUtils {


    private final OkHttpClient client;
    GithubProperties githubProperties;


    public OkHttpUtils(GithubProperties githubProperties) {
        this.githubProperties = githubProperties;
        this.client = new OkHttpClient().newBuilder().build();
    }


    private String currentDateAndTime() {
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 00, 00));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return now.format(formatter);
    }

    public String get() {
        try {
            String currentDate = currentDateAndTime();
            String token = "Bearer " + this.githubProperties.getToken();
            String url = "https://api.github.com/user/repos?sort=updated&since=" + currentDate;
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Accept", "application/vnd.github+json")
                    .addHeader("Authorization", token)
                    .addHeader("X-GitHub-Api-Version", "2022-11-28")
                    .build();
            Response response = this.client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
