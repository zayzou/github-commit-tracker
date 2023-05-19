/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : DateFormatter.java
 */

package com.zayzou.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.now().format(formatter); // Get current date in ISO_DATE format
    }
}
