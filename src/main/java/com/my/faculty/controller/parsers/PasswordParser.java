package com.my.faculty.controller.parsers;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Oleksii Petrokhalko.
 */
public class PasswordParser extends Parser<String> {
    private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");
    private static final String INCORRECT_PASSWORD = "Incorrect password, 6 to 20 characters with at least one " +
            "digit, one upper, lower case letter";
    private StringParser stringParser = new StringParser();
    private Map<String, Object> errors;

    public PasswordParser(Map<String, Object> errors) {
        this.errors = errors;
    }

    @Override
    public String parse(String key, String[] params) {
        String password = stringParser.parse(key, params);
        if (errors.containsKey(createErrorKey(key))) {
            return password;
        }
        if (!validate(password)) {
            errors.put(createErrorKey(key), INCORRECT_PASSWORD);
        }
        return password;
    }

    private static boolean validate(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
}
