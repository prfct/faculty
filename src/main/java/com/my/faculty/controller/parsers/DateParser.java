package com.my.faculty.controller.parsers;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Stein on 28.11.16.
 */
public class DateParser extends Parser<Date> {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final String WRONG_DATE_FORMAT = "Date incorrect, should be dd/MM/yyyy HH:mm ";
    private Map<String, Object> errors;

    public DateParser(Map<String, Object> errors) {
        this.errors = errors;
    }

    @Override
    public Date parse(String key, String[] params) {
        Date result = null;
        try {
            if (params != null && params.length != 0) {
                result =  DATE_FORMAT.parse(params[0]);
            }
        } catch (ParseException e) {
            errors.put(createErrorKey(key), WRONG_DATE_FORMAT);
        }
        return result;
    }
}
