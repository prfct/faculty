package com.my.faculty.web;

import com.my.faculty.controller.parsers.Parser;

import java.util.Map;

/**
 * @author Oleksii Petrokhalko.
 */
public interface Model {
    void setAttribute(String key, Object o);

    void setAttributes(Map<String, Object> attributes);

    String findParameter(String key);

    <T> T findParameter(String key, Parser<T> parser);

    void putSessionAttribute(String s, Object o);

    Object getSessionAttribute(String s);

    void setCookie(String key, String val);

    void invalidateSession();

}
