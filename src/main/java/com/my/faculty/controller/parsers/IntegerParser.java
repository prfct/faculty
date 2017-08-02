package com.my.faculty.controller.parsers;

import java.util.Map;

/**
 * Created by Stein on 14.12.16.
 */
public class IntegerParser extends NumberParser<Integer> {

    public IntegerParser(Map<String, Object> errors) {
        super(errors);
    }

    @Override
    public Integer parse(String key, String[] params) {
        Long val = parseLong(key, params);
        return val == null ? null : val.intValue();
    }
}
