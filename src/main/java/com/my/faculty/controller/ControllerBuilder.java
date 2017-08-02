package com.my.faculty.controller;

import com.my.faculty.web.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleksii Petrokhalko.
 */
public class ControllerBuilder {
    private Map<String, Map<HttpMethod, ControllerCommand>> controllers = new HashMap<>();

    public ControllerBuilder register(String path, ControllerCommand controller) {
        return register(path, HttpMethod.GET, controller);
    }

    public ControllerBuilder register(String path, HttpMethod method, ControllerCommand controller) {
        Map<HttpMethod, ControllerCommand> map = findControllerMap(path);
        map.put(method, controller);
        return this;
    }

    public Map<HttpMethod, ControllerCommand> findControllerMap(String path) {
        Map<HttpMethod, ControllerCommand> map;
        if (controllers.containsKey(path)) {
            map = controllers.get(path);
        } else {
            map = new HashMap<>();
        }
        controllers.put(path, map);
        return map;
    }

    public Map<String, Map<HttpMethod, ControllerCommand>> build() {
        return controllers;
    }

}
