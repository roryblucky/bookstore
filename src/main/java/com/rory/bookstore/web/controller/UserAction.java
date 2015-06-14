package com.rory.bookstore.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RoryGao on 15/6/13.
 */
public class UserAction {
    public String login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hah");
        return "index.jsp";
    }
}
