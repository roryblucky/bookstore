package com.rory.bookstore.web.filter;

import com.rory.bookstore.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by RoryGao on 15/6/13.
 */
public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);

        chain.doFilter(new MyHttpRequestWrapper(request), response);
//        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        String configEncoding = config.getInitParameter("encoding");
        if (!StringUtils.isEmpty(configEncoding)) {
            this.encoding = configEncoding;
        }
    }

    class MyHttpRequestWrapper extends HttpServletRequestWrapper {
        private HttpServletRequest request;

        public MyHttpRequestWrapper(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            String value = request.getParameter(name);

            if (value == null)
                return null;

            if ("GET".equalsIgnoreCase(request.getMethod())) {
                try {
                    value = new String(value.getBytes("ISO-8859-1"), request.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                }
            }
            return value;
        }
    }

}
