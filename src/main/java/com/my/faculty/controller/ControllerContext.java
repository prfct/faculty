package com.my.faculty.controller;

import com.my.faculty.common.Page;
import com.my.faculty.controller.course.CreateCourseController;
import com.my.faculty.controller.course.ShowCourseListController;
import com.my.faculty.controller.course.ShowCreateCoursePageController;
import com.my.faculty.controller.user.CreateUserController;
import com.my.faculty.controller.user.LoginUserController;
import com.my.faculty.controller.user.ShowLoginPageController;
import com.my.faculty.util.Language;
import com.my.faculty.util.ResourceUtil;
import com.my.faculty.web.DispatcherModel;
import com.my.faculty.web.HttpMethod;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.my.faculty.common.Key.*;
import static com.my.faculty.common.Redirect.REDIRECT;

/**
 * @author Oleksii Petrokhalko.
 */
public class ControllerContext {
    private Map<String, Map<HttpMethod, ControllerCommand>> controllers;


    private ControllerContext() {
        initControllers();
    }

    private static class InstanceHolder {
        private static final ControllerContext INSTANCE = new ControllerContext();
    }

    public static ControllerContext getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void initControllers() {
        controllers = new ControllerBuilder()
                .register("/login", new ShowLoginPageController())
                .register("/login", HttpMethod.POST, new LoginUserController())
                .register("/registration", HttpMethod.POST, new CreateUserController())
                .register("/course/list", new ShowCourseListController())
                .register("/course/create", new ShowCreateCoursePageController())
                .register("/course/create", HttpMethod.POST, new CreateCourseController())
                .register("/localization", HttpMethod.POST, new LocalizationController())
                .build();

    }

    public void process(HttpMethod httpMethod, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DispatcherModel model = new DispatcherModel();
        Language language = Language.fromCode(getCurrentLanguageCode(request));
        model.setCurrentLanguage(language);
        model.setParameters(request.getParameterMap());
        model.setSession(request.getSession());
        String path = executeBusinessLogic(httpMethod, model, request);
        for (Map.Entry<String, Object> attributes : model.getAttributes().entrySet()) {
            request.setAttribute(attributes.getKey(), attributes.getValue());
        }
        for (Map.Entry<String, String> cookieEntry : model.getCookies().entrySet()) {
            Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
            cookie.setMaxAge(ONE_YEAR);
            response.addCookie(cookie);
        }
        ResourceUtil.getLocalizeResourceBundle(language);
        request.setAttribute(LANGUAGE, language.getCode());
        if (!redirectIfNecessary(path, response)) {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    private String getCurrentLanguageCode(HttpServletRequest request) {
        String result = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LANGUAGE.equals(cookie.getName())) {
                    result = cookie.getValue();
                    break;
                }
            }
        }
        return result;
    }

    private String executeBusinessLogic(HttpMethod httpMethod, DispatcherModel model, HttpServletRequest request) {
        ControllerCommand controller = findController(request.getPathInfo(), httpMethod);
        if (controller != null) {
            try {
                String path = controller.execute(model);
                return path;
            } catch (Exception e) {
                return Page.ERROR;
            }
        } else {
            return Page.BAD_URL;
        }
    }

    private boolean redirectIfNecessary(String url, HttpServletResponse response) throws ServletException, IOException {
        if (url.startsWith(REDIRECT)) {
            response.sendRedirect(url.substring(url.indexOf(URI_SEPARATOR) + ONE));
            return true;
        }
        return false;
    }

    private ControllerCommand findController(String path, HttpMethod httpMethod) {
        Map<HttpMethod, ControllerCommand> controllersMap = controllers.get(path);
        ControllerCommand controller = null;
        if (controllersMap != null && controllersMap.containsKey(httpMethod)) {
            controller = controllersMap.get(httpMethod);
        }
        return controller;
    }
}
