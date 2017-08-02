package com.my.faculty.persistance.db;

/**
 * @author Oleksii Petrokhalko.
 */
public interface DataCommand<T> {
    T execute();
}
