package com.my.faculty.web;

import com.my.faculty.controller.ControllerContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Oleksii Petrokhalko.
 * 1. Система Факультатив. Существует перечень Курсов, за каждым из
 * которых закреплен один Преподаватель. Студент записывается на один или
 * несколько Курсов. По окончании обучения Преподаватель выставляет Студенту
 * и добавляет отзыв.
 */
public class DispatcherServlet extends HttpServlet {
    private ControllerContext cc = ControllerContext.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cc.process(HttpMethod.GET, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cc.process(HttpMethod.POST, req, resp);
    }
}
