package com.zayzou.utils;

import com.zayzou.GithubProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class OkHttpUtils {

    public static final String API_VERSION_HEADER = "2022-11-28";
    public static final String ACCECPT_HEADER = "application/vnd.github+json";
    private final OkHttpClient client;
    GithubProperties githubProperties;

    public OkHttpUtils(GithubProperties githubProperties) {
        this.githubProperties = githubProperties;
        this.client = new OkHttpClient().newBuilder().build();
    }

    private static String getString(String string, String regex) {
        return string.replaceAll(regex, "");
    }

    private String getContributionForDate(String input) {
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


    private String getContributionForDate(String string, String date) {
        Document doc = Jsoup.parse(string);
        String value = doc.getElementsByAttributeValue("data-date", date).first().ownText();
        return value;
    }

    private String currentDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return now.format(formatter);
    }

    public String get() {
        try {
            String currentDate = currentDate();
            String url = String.format("https://github.com/users/%s/contributions?to=%s",
                    this.githubProperties.getUsername(), currentDate);
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Accept", ACCECPT_HEADER)
                    .addHeader("Authorization", "Bearer " + this.githubProperties.getToken())
                    .addHeader("X-GitHub-Api-Version", API_VERSION_HEADER)
                    .build();
            Response response = this.client.newCall(request).execute();
            String responseValue = response.body().string();
            return getContributionForDate(responseValue, currentDate);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
