package com.my.faculty.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Stein on 16.12.16.
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getPathInfo() == null || req.getPathInfo().startsWith("/views")) {
            filterChain.doFilter(request, response);
            return;
        }
        if ("/login".equals(req.getPathInfo())
                || req.getSession().getAttribute("auth") != null
                || "/registration".equals(req.getPathInfo())
                || "/localization".equals(req.getPathInfo())) {
            filterChain.doFilter(request, response);
        } else {
            resp.sendRedirect("/app/login");
        }
    }

    @Override
    public void destroy() {
        //do nothing
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //do nothing
    }
}
