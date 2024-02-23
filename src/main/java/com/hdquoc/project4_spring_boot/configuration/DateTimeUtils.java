package com.hdquoc.project4_spring_boot.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        if(dateTime == null) {
            return "null";
        }
        return dateTime.format(formatter);
    }
}
