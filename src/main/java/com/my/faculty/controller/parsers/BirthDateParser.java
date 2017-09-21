package com.my.faculty.controller.parsers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

/**
 * Created by Stein on 28.11.16.
 */
public class BirthDateParser extends Parser<LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String WRONG_DATE_FORMAT = "registration.error.incorrectBirthday";
    private Map<String, Object> errors;

    public BirthDateParser(Map<String, Object> errors) {
        this.errors = errors;
    }

    @Override
    public LocalDate parse(String key, String[] params) {
        LocalDate result = null;
        try {
            if (params != null && params.length != 0) {
                result = LocalDate.parse(params[0], FORMATTER);
            }
        } catch (DateTimeParseException e) {
            errors.put(createErrorKey(key), WRONG_DATE_FORMAT);
        }
        return result;
    }
}
