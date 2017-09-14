package com.my.faculty.controller.parsers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

/**
 * Created by Stein on 28.11.16.
 */
public class BirthDateParser extends Parser<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String WRONG_DATE_FORMAT = "BirthDate incorrect, should be mm-dd-yyyy ";
    private Map<String, Object> errors;

    public BirthDateParser(Map<String, Object> errors) {
        this.errors = errors;
    }

    @Override
    public LocalDateTime parse(String key, String[] params) {
        LocalDateTime result = null;
        try {
            if (params != null && params.length != 0) {
                LocalDate ld = LocalDate.parse(params[0], FORMATTER);
                result = LocalDateTime.of(ld, LocalDateTime.MIN.toLocalTime());
            }
        } catch (DateTimeParseException e) {
            errors.put(createErrorKey(key), WRONG_DATE_FORMAT);
        }
        return result;
    }
}
