/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : RootController.java
 */

package com.zayzou.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class RootController {


}
