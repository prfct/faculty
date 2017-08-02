package com.my.faculty.persistance.dao;

import com.my.faculty.persistance.db.DataCommand;

/**
 * @author Oleksii Petrokhalko.
 */
public abstract class AbstractDao {

    protected <T> T executeDataCommand(DataCommand<T> command) {
        return command.execute();
    }
}
