package com.my.faculty.persistance.db;


import java.sql.Connection;

public interface AbstractConnection extends AutoCloseable {

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    Connection getConnection();

    @Override
    void close();
}
