package com.my.faculty.controller.parsers;


/**
 * Created by Stein on 05.05.17.
 */
public class BooleanParser extends Parser<Boolean> {

    @Override
    public Boolean parse(String key, String[] params) {
        Boolean result = false;
        if (params != null && params.length != 0) {
            result = Boolean.parseBoolean(params[0]);
        }
        return result;
    }
}
