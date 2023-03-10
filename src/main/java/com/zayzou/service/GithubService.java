package com.zayzou.service;

import com.zayzou.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GithubService {

    OkHttpUtils okHttpUtils;

    public GithubService(OkHttpUtils okHttpUtils) {
        this.okHttpUtils = okHttpUtils;
    }

    public String getUpdates() {
        return this.okHttpUtils.get();
    }
}
