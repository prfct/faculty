package com.my.faculty.web.filter;

import com.my.faculty.common.Page;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.my.faculty.common.Key.AUTH;
import static com.my.faculty.common.Key.ONE;
import static com.my.faculty.domain.UserRole.ADMIN;
import static com.my.faculty.domain.UserRole.TEACHER;

/**
 * Created by Stein on 16.12.16.
 */
public class AuthenticationFilter implements Filter {
    private static final Pattern URL_PATTERN = Pattern.compile("([^\\?]+)(\\?.*)?");
    private Map<String, List<UserRole>> pathPermissionsMap = new HashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Auth auth = findAuth(req);
        String path = parsePath(req.getPathInfo());

        if (!pathPermissionsMap.containsKey(path)) {
            req.getRequestDispatcher(Page.BAD_URL).forward(request, response);
        } else if (hasPermission(path, auth)) {
            filterChain.doFilter(request, response);
        } else if (auth == null) {
            resp.sendRedirect("/app/login");
        } else {
            req.getRequestDispatcher(Page.NOT_ACCESS).forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pathPermissionsMap.put("/login", Collections.emptyList());
        pathPermissionsMap.put("/registration", Collections.emptyList());
        pathPermissionsMap.put("/course/list", Collections.emptyList());
        pathPermissionsMap.put("/localization", Collections.emptyList());
        pathPermissionsMap.put("/logout", Collections.emptyList());
        pathPermissionsMap.put("/course/assign", Arrays.asList(UserRole.values()));
        pathPermissionsMap.put("/user/courses", Arrays.asList(UserRole.values()));
        pathPermissionsMap.put("/course/create", Collections.singletonList(ADMIN));
        pathPermissionsMap.put("/course/detail", Arrays.asList(UserRole.values()));
        pathPermissionsMap.put("/user/list", Collections.singletonList(ADMIN));
        pathPermissionsMap.put("/user/update", Collections.singletonList(ADMIN));
        pathPermissionsMap.put("/teacher/students", Collections.singletonList(TEACHER));
        pathPermissionsMap.put("/student/update", Collections.singletonList(TEACHER));
    }

    @Override
    public void destroy() {
    }

    private Auth findAuth(HttpServletRequest request) {
        return (Auth) request.getSession().getAttribute(AUTH);
    }

    private boolean hasPermission(String path, Auth auth) {
        List<UserRole> roles = pathPermissionsMap.get(path);
        if (roles == null) {
            return false;
        } else if (roles.isEmpty()) {
            return true;
        } else if (auth != null) {
            return roles.contains(auth.getUserRole());
        }
        return false;
    }

    private String parsePath(String path) {
        String result = null;
        if (path != null) {
            Matcher matcher = URL_PATTERN.matcher(path);
            if (matcher.matches()) {
                result = matcher.group(ONE);
            }
        }
        return result;
    }
}
