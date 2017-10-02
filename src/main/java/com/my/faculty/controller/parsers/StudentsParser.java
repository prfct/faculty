package com.my.faculty.controller.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentsParser extends Parser<List<Long>> {
    private static final String SELECT_STUDENT = "Please select at least one student";
    Map<String, Object> errors;

    public StudentsParser(Map<String, Object> errors) {
        this.errors = errors;
    }


    @Override
    public List<Long> parse(String key, String[] params) {
        List<Long> studentsIds = new ArrayList<>();
        if (params == null || params.length == 0) {
            errors.put(createErrorKey(key), SELECT_STUDENT);
            return studentsIds;

        }
        return null;
    }
}
