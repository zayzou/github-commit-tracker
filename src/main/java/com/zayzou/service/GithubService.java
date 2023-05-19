package com.zayzou.service;

import com.zayzou.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.zayzou.utils.DateFormatter.getCurrentDate;

@Service
@Slf4j
public class GithubService {

    private final String GITHUB_CONTRIBUTIONS_URL_FORMAT = "https://github.com/users/%s/contributions?to=%s"; // URL format for fetching Github contributions data
    private final OkHttpUtils okHttpUtils; // OkHttpUtils instance for making HTTP calls
    @Value(("${github.username}"))
    private String githubUsername; // Github username injected from properties file

    public GithubService(OkHttpUtils okHttpUtils) {
        this.okHttpUtils = okHttpUtils;
    }

    private Document parseHtml(String html) {
        return Jsoup.parse(html); // Parse HTML string to Jsoup Document
    }


    private String getContribution(String html, String attribute, String value) {
        Document doc = parseHtml(html);
        return doc.getElementsByAttributeValue(attribute, value).first().ownText(); // Extract contribution data from HTML using attribute and value
    }

    public String getTodayContribution() {
        String currentDate = getCurrentDate(LocalDate.now()).substring(0, 10);//remove time part of the date
        String url = String.format(GITHUB_CONTRIBUTIONS_URL_FORMAT, githubUsername, currentDate); // Format the Github contributions URL with current date and username
        String responseValue = okHttpUtils.httpCall(url, "GET"); // Make HTTP GET call to Github contributions URL
        return getContribution(responseValue, "data-date", currentDate); // Extract today's contribution from the response
    }

    public String getCurrentYearContribution() {
        String currentDate = getCurrentDate(LocalDate.now());
        String url = String.format(GITHUB_CONTRIBUTIONS_URL_FORMAT, githubUsername, currentDate); // Format the Github contributions URL with current date and username
        String responseValue = okHttpUtils.httpCall(url, "GET"); // Make HTTP GET call to Github contributions URL
        return getContribution(responseValue, "class", "f4 text-normal mb-2"); // Extract current year's contribution from the response
    }
}
