package com.my.faculty.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Stein on 15.12.16.
 */

public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String encodingRequest = request.getCharacterEncoding();
        if (encoding != null && !encoding.equals(encodingRequest)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //do nothing
    }
}
