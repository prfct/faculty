package com.my.faculty.persistance.db;


public interface AbstractConnection extends AutoCloseable {

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    @Override
    void close();
}
