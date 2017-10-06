package com.my.faculty.controller.parsers;


import java.util.Map;

/**
 * Created by Stein on 20.12.16.
 */
public abstract class NumberParser<T extends Number> extends Parser<T> {
    private static final String NUMBER = "number.error.text";
    private static final String NOTNULL = "notnull.error.text";
    private static final String MAX_LENGTH = "max_length.error.text";
    private int maxLength = Integer.MAX_VALUE;
    private StringParser stringParser = new StringParser();
    private Map<String, Object> errors;

    NumberParser(Map<String, Object> errors) {
        this.errors = errors;
    }

    Long parseLong(String key, String[] params) {
        String param = stringParser.parse(key, params);
        Long result = null;
        if (param != null && !param.isEmpty()) {
            try {
                result = Long.parseLong(param);
                if (result > maxLength) {
                    errors.put(createErrorKey(key), MAX_LENGTH);
                }
            } catch (NumberFormatException e) {
                errors.put(createErrorKey(key), NUMBER);
            }
        } else {
            errors.put(createErrorKey(key), NOTNULL);
        }
        return result;
    }
}
