package com.my.faculty.web;

import com.my.faculty.controller.parsers.Parser;
import com.my.faculty.util.Language;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Oleksii Petrokhalko.
 */
public class DispatcherModel implements Model {
    private Map<String, Object> attributes = new HashMap<>();
    private Map<String, String[]> parameters;
    private HttpSession session;
    private Map<String, String> cookies = new HashMap<>();
    private Language currentLanguage;

    @Override
    public void setAttribute(String key, Object o) {
        attributes.put(key, o);
    }

    @Override
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes.putAll(attributes);
    }

    @Override
    public String findParameter(String key) {
        String result = null;
        String[] params = parameters.get(key);
        if (params != null && params.length > 0) {
            result = params[0];
        }
        return result;
    }

    @Override
    public <T> T findParameter(String key, Parser<T> parser) {
        return parser.parse(key, parameters.get(key));
    }

    @Override
    public void putSessionAttribute(String s, Object o) {
        session.setAttribute(s, o);
    }

    @Override
    public Object getSessionAttribute(String s) {
        return session.getAttribute(s);
    }

    @Override
    public void setCookie(String key, String val) {
        cookies.put(key, val);
    }

    public void setCurrentLanguage(Language currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
}
