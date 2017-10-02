package com.my.faculty.controller.parsers;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Oleksii Petrokhalko.
 */
public class TitleParser extends Parser<String> {
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{2,10}$");
    private static final String INCORRECT_TITLE = "course.error.title";
    private StringParser stringParser = new StringParser();
    private Map<String, Object> errors;

    public TitleParser(Map<String, Object> errors) {
        this.errors = errors;
    }

    @Override
    public String parse(String key, String[] params) {
        String name = stringParser.parse(key, params);
        if (errors.containsKey(createErrorKey(key))) {
            return name;
        }
        if (!validate(name)) {
            errors.put(createErrorKey(key), INCORRECT_TITLE);
        }
        return name;
    }

    private static boolean validate(String password) {
        Matcher matcher = VALID_NAME_REGEX.matcher(password);
        return matcher.find();
    }
}
