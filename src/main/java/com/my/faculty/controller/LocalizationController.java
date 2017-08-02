package com.my.faculty.controller;

import com.my.faculty.web.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.my.faculty.common.Key.CURRENT_URL;
import static com.my.faculty.common.Key.LANGUAGE;
import static com.my.faculty.common.Key.TWO;
import static com.my.faculty.common.Redirect.COURSE_LIST;
import static com.my.faculty.common.Redirect.REDIRECT;

/**
 * @author Oleksii Petrokhalko.
 */
public class LocalizationController implements ControllerCommand {
    private static final Pattern URL_PATTERN = Pattern.compile("(http:\\/\\/[^\\/]+)(.+)");

    @Override
    public String execute(Model model) {
        String url = model.findParameter(CURRENT_URL);
        String language = model.findParameter(LANGUAGE);
        model.setCookie(LANGUAGE, language);
        return findRedirectPath(url);
    }

    private String findRedirectPath(String url) {
        String result = COURSE_LIST;
        Matcher matcher = URL_PATTERN.matcher(url);
        if (matcher.matches()) {
            result = matcher.group(TWO);
            result = REDIRECT + result;
        }
        return result;
    }
}
