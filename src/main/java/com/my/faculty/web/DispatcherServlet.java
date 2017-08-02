package com.my.faculty.web;

import com.my.faculty.context.ApplicationContext;
import com.my.faculty.controller.ControllerContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Oleksii Petrokhalko.
 */
public class DispatcherServlet extends HttpServlet {
    private static ControllerContext cc = ApplicationContext.getControllerContext();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cc.process(HttpMethod.GET, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cc.process(HttpMethod.POST, req, resp);
    }
}
