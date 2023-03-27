package com.zayzou.controller;

import com.zayzou.configuration.NotionConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/notion")
public class NotionController {

    NotionConfigProperties notion;

    public NotionController(NotionConfigProperties notion) {
        this.notion = notion;
    }

    @GetMapping
    public Map<String, String> printAllProps() {
        return Map.of(
                "apiUtl", notion.apiUrl(),
                "apiVersion", notion.apiVersion(),
                "authToken", notion.authToken(),
                "databaseId", notion.databaseId()
        );
    }
}
