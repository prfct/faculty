package com.my.faculty.web;

/**
 * @author Oleksii Petrokhalko.
 */
public enum HttpMethod {
    GET("GET"), POST("POST");

    private String name;

    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
