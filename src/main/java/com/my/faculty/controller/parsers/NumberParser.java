package com.my.faculty.controller.parsers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Stein on 20.12.16.
 */
public abstract class NumberParser<T extends Number> extends Parser<T> {
    private static final String NUMBER = "Should be number";
    private static final String NOTNULL = "Should be notnull";
    private String maxLengthErrorMessage = "Should be shorter";
    private int maxLength = Integer.MAX_VALUE;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private Map<String, Object> errors;

    NumberParser(Map<String, Object> errors) {
        this.errors = errors;
    }

    Long parseLong(String key, String[] params) {
        Long result = null;
        if (params != null && params.length != 0) {
            String param = params[0];
            if (param != null && !param.isEmpty()) {
                try {
                    result = Long.parseLong(param);
                    if (result > maxLength){
                        errors.put(createErrorKey(key), maxLengthErrorMessage);
                    }
                } catch (NumberFormatException e) {
                    LOGGER.warn(e.getMessage(), e);
                    errors.put(createErrorKey(key), NUMBER);
                }
            } else {
                errors.put(createErrorKey(key), NOTNULL);
            }
        }
        return result;
    }

    public String getMaxLengthErrorMessage() {
        return maxLengthErrorMessage;
    }

    public void setMaxLengthErrorMessage(String maxLengthErrorMessage) {
        this.maxLengthErrorMessage = maxLengthErrorMessage;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
