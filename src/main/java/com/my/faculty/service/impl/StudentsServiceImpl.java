package com.my.faculty.service.impl;

import com.my.faculty.service.StudentsService;

import java.util.Set;

public class StudentsServiceImpl implements StudentsService {

    private StudentsServiceImpl() {
    }

    private static class InstanceHolder {
        private static final StudentsServiceImpl INSTANCE = new StudentsServiceImpl();
    }

    public static StudentsService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Set<StudentsService> students() {
        return null;
    }
}
