package com.my.faculty.common.builders;

public abstract class AbstractCreator {
    public  UserBuilder getUserCreator() {
        return new UserBuilder();
    }

}
