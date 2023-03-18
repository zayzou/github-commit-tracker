package com.zayzou.service;

import com.zayzou.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class GithubService {

    private final String githubUsername;
    OkHttpUtils okHttpUtils;

    public GithubService(OkHttpUtils okHttpUtils) {
        this.okHttpUtils = okHttpUtils;
        githubUsername = "Soffi-Zahir";
    }


    private String getContributionForDate(String string, String date) {
        Document doc = Jsoup.parse(string);
        return doc.getElementsByAttributeValue("data-date", date).first().ownText();
    }

    private String getContributionForYear(String string) {
        Document doc = Jsoup.parse(string);
        return doc.getElementsByTag("h2").first().ownText();

    }

    private String currentDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return now.format(formatter);
    }

    public String getUpdates() {
        String currentDate = currentDate();
        var responseValue = this.okHttpUtils.httpCall(
                "https://github.com/users/" + githubUsername + "/contributions?to=" + currentDate,
                "GET");
        log.info(responseValue);
        return getContributionForDate(responseValue, currentDate);
    }

    public String getAllContribution() {
        String currentDate = currentDate();
        var responseValue = this.okHttpUtils.httpCall(
                "https://github.com/users/" + githubUsername + "/contributions?to=" + currentDate,
                "GET");
        log.info(responseValue);
        return getContributionForYear(responseValue);
    }


}
