package com.my.faculty.controller.parsers;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stein on 19.12.16.
 */
public class EmailParser  extends Parser<String> {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String INCORRECT_EMAIL = "registration.error.incorrectEmail";
    private StringParser stringParser = new StringParser();
    private Map<String, Object> errors;

    public EmailParser(Map<String, Object> errors) {
        this.errors = errors;
    }

    @Override
    public String parse(String key, String[] params) {
        String email = stringParser.parse(key, params);
        if (errors.containsKey(createErrorKey(key))) {
            return email;
        }
        if (!validate(email)) {
            errors.put(createErrorKey(key), INCORRECT_EMAIL);
        }
        return email;
    }

    private static boolean validate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
