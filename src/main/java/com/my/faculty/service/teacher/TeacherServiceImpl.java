package com.my.faculty.service.teacher;

import com.my.faculty.domain.Teacher;
import com.my.faculty.persistance.dao.DaoFactory;

public class TeacherServiceImpl implements TeacherService {
    private DaoFactory daoFactory = DaoFactory.getMySqlDaoFactory();

    private TeacherServiceImpl() {
    }

    private static class InstanceHolder {
        private static final TeacherServiceImpl INSTANCE = new TeacherServiceImpl();
    }

    public static TeacherService getInstance() {
        return InstanceHolder.INSTANCE;
    }


    @Override
    public Teacher create(Long userId) {

        return null;
    }
}
