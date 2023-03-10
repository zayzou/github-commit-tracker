package com.zayzou.service;

import com.zayzou.GithubProperties;
import org.springframework.stereotype.Service;

@Service
public class GithubService {

    GithubProperties githubProperties;

    public GithubService(GithubProperties githubProperties) {
        this.githubProperties = githubProperties;
    }

    public void fetch(){

    }
}
