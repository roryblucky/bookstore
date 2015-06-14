package com.rory.bookstore.web.filter;

import com.rory.bookstore.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by RoryGao on 15/6/13.
 */
public class ControlFilter implements Filter {

    private Map<String, String> actionMap;

    @SuppressWarnings("unchecked")
    public void init(FilterConfig config) throws ServletException {
        this.actionMap = (Map<String, String>) config.getServletContext().getAttribute("actionMap");
    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        String result = invokeMethod(request, response);
        if (!StringUtils.isEmpty(result)) {
            if (result.contains("redirect")) {
                response.sendRedirect(request.getContextPath() + "/" + result.substring(result.lastIndexOf(":") + 1));
            } else {
                request.getRequestDispatcher(result).forward(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private String invokeMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        String action = servletPath.substring(servletPath.lastIndexOf("/") + 1, servletPath.lastIndexOf("."));
        String[] actionWithMethod = action.split("_");
        String className = actionMap.get(actionWithMethod[0].toLowerCase());
        String methodName = actionWithMethod.length > 1 ? actionWithMethod[1] : "execute";

        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            return (String) method.invoke(clazz.newInstance(), request, response);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


    public void destroy() {
    }

}
