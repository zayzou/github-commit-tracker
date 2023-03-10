package com.zayzou.utils;

import com.zayzou.GithubProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OkHttpUtils {


    GithubProperties githubProperties;
    private OkHttpClient client;


    public OkHttpUtils(GithubProperties githubProperties) {
        this.githubProperties = githubProperties;
        this.client = new OkHttpClient().newBuilder().build();
    }


    public String get() {
        try {
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            String currentDate = "2023-03-11T10:30:00Z";
            String token = this.githubProperties.getToken();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user/repos?sort=updated&since=" + currentDate)
                    .addHeader("Accept", "application/vnd.github+json")
                    .addHeader("Authorization", "Bearer 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")
                    .addHeader("X-GitHub-Api-Version", "2022-11-28")
                    .build();
            Response response = this.client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
