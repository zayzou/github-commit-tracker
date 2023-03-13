package com.zayzou.utils;

import com.zayzou.GithubProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class OkHttpUtils {

    private final OkHttpClient client;
    GithubProperties githubProperties;

    public OkHttpUtils(GithubProperties githubProperties) {
        this.githubProperties = githubProperties;
        this.client = new OkHttpClient().newBuilder().build();
    }

    private static String getString(String string, String regex) {
        return string.replaceAll(regex, "");
    }

    private String getValue(String input) {
        input = getString(input, "\\s");
        String regex = ">\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String number = matcher.group();
            return getString(number, ">");
        } else {
            return "0";
        }


    }

    private String currentDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return now.format(formatter);
    }

    public String get() {
        try {
            String currentDate = currentDate();
            String token = "Bearer " + this.githubProperties.getToken();
            String url = "https://github.com/users/Soffi-Zahir/contributions?to=" + currentDate;
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Accept", "application/vnd.github+json")
                    .addHeader("Authorization", token)
                    .addHeader("X-GitHub-Api-Version", "2022-11-28")
                    .build();
            Response response = this.client.newCall(request).execute();
            String responseValue = response.body().string();
            return getValue(responseValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
