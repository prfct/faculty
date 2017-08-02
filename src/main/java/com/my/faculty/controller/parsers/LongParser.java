package com.my.faculty.controller.parsers;


import java.util.Map;

/**
 * Created by Stein on 20.12.16.
 */
public class LongParser extends NumberParser<Long> {

    public LongParser(Map<String, Object> errors) {
        super(errors);
    }

    @Override
    public Long parse(String key, String[] params) {
        return parseLong(key, params);
    }
}
