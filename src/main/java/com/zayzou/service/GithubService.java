package com.zayzou.service;

import com.zayzou.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class GithubService {

    private final String GITHUB_CONTRIBUTIONS_URL_FORMAT = "https://github.com/users/%s/contributions?to=%s";

    @Value(("${github.username}"))
    private String githubUsername;
    private final OkHttpUtils okHttpUtils;

    public GithubService(OkHttpUtils okHttpUtils) {
        this.okHttpUtils = okHttpUtils;
    }

    private Document parseHtml(String html) {
        return Jsoup.parse(html);
    }

    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return LocalDate.now().format(formatter);
    }

    private String getContribution(String html, String attribute, String value) {
        Document doc = parseHtml(html);
        return doc.getElementsByAttributeValue(attribute, value).first().ownText();
    }

    public String getTodayContribution() {
        String currentDate = getCurrentDate();
        String url = String.format(GITHUB_CONTRIBUTIONS_URL_FORMAT, githubUsername, currentDate);
        String responseValue = okHttpUtils.httpCall(url, "GET");
        return getContribution(responseValue, "data-date", currentDate);
    }

    public String getCurrentYearContribution() {
        String currentDate = getCurrentDate();
        String url = String.format(GITHUB_CONTRIBUTIONS_URL_FORMAT, githubUsername, currentDate);
        String responseValue = okHttpUtils.httpCall(url, "GET");
        return getContribution(responseValue, "class", "f4 text-normal mb-2");
    }
}
