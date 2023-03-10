package com.zayzou.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo", produces = "applciation/json")
public class DemoController {


    @GetMapping
    public String demo() {

        return "";

    }
}
