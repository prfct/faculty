package com.my.faculty.controller;


import com.my.faculty.web.Model;

/**
 * @author Oleksii Petrokhalko.
 */
public interface ControllerCommand {
    String execute(Model model);
}
