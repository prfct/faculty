package com.my.faculty.controller.parsers;

/**
 * Created by Stein on 28.11.16.
 */
public abstract class Parser<T> {
    private static final String ERROR_POSTFIX = "_error";

    public abstract T parse(String key, String[] params);

    String createErrorKey(String key) {
        return key + ERROR_POSTFIX;
    }
}
