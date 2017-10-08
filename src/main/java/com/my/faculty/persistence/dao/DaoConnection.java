package com.my.faculty.persistence.dao;


public interface DaoConnection extends AutoCloseable {

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    @Override
    void close();
}
