package com.mipres.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UsoFechas {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_FORMATTER_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String dateNow() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    public String dateTimeNow() {
        return LocalDateTime.now().format(DATE_FORMATTER_WITH_TIME);
    }

    public String dateBefore(int dias) {
        return LocalDate.now().minusDays(dias).format(DATE_FORMATTER);
    }

    public List<String> getDatesBetweenTodayAndPast(int daysBefore) {
        LocalDate start = LocalDate.parse(dateBefore(daysBefore), DATE_FORMATTER);
        LocalDate end = LocalDate.parse(dateNow(), DATE_FORMATTER);

        List<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.format(DATE_FORMATTER));
            start = start.plusDays(1);
        }
        return totalDates;
    }
}
