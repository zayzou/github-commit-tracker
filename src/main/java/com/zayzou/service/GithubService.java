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

    OkHttpUtils okHttpUtils;

    public GithubService(OkHttpUtils okHttpUtils) {
        this.okHttpUtils = okHttpUtils;
    }


    private String getContributionForDate(String string, String date) {
        Document doc = Jsoup.parse(string);
        return doc.getElementsByAttributeValue("data-date", date).first().ownText();
    }

    private String currentDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return now.format(formatter);
    }

    public String getUpdates() {
        var responseValue = this.okHttpUtils.httpCall(
                "https://github.com/users/Soffi-Zahir/contributions?to=2023-01-01",
                "GET");
        return getContributionForDate(responseValue, currentDate());
    }
}
