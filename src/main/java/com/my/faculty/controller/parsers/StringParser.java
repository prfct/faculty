package com.my.faculty.controller.parsers;


/**
 * Created by Stein on 20.12.16.
 */
public class StringParser extends Parser<String> {

    @Override
    public String parse(String key, String[] params) {
        String str = null;
        if (params != null && params.length != 0) {
            str = params[0];
        }
        return str;
    }
}
