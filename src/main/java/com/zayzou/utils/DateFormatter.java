/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : DateFormatter.java
 */

package com.zayzou.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static String getCurrentDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return date.format(formatter); // Get current date in ISO_DATE format

    }
}
