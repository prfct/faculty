package com.my.faculty.persistance.db;

public interface ConnectionPool {
    AbstractConnection getConnection();
}
