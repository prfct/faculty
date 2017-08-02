package com.my.faculty.controller.parsers;

/**
 * Created by Stein on 07.05.17.
 */
public class IdParser extends Parser<Long> {

    @Override
    public Long parse(String key, String[] params) {
        Long result = null;
        if (params != null && params.length != 0) {
            String param = params[0];
            if (param != null && !param.isEmpty()) {
                result = Long.parseLong(param);
            }
        }
        return result;
    }
}
