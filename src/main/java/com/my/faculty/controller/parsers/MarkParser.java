package com.my.faculty.controller.parsers;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkParser extends LongParser {
    private static final String INCORRECT_MARK = "mark.error.incorrectMark";
    private Map<String, Object> errors;

    public MarkParser(Map<String, Object> errors) {
        super(errors);
        this.errors = errors;
    }

    @Override
    public Long parse(String key, String[] params) {
        Long mark = super.parse(key, params);
        if (mark == null || mark > 10 || mark <= 0) {
            errors.put(createErrorKey(key), INCORRECT_MARK);
        }
        return mark;
    }
}
