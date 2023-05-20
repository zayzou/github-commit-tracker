/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : OkHttpUtils.java
 */

package com.zayzou.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OkHttpUtils {

    private final OkHttpClient client;

    public OkHttpUtils() {
        this.client = new OkHttpClient().newBuilder().build();
    }

    public void httpCall(String url, String method) {
        try {
            RequestBody body = method.equals("POST") ? RequestBody.create("", MediaType.parse("text/plain")) : null;
            Request request = new Request.Builder()
                    .url(url)
                    .method(method, body)
                    .build();
            client.newCall(request).execute();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
