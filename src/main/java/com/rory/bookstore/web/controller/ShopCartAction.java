package com.rory.bookstore.web.controller;

import com.rory.bookstore.domain.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by RoryGao on 2015/6/23.
 */
public class ShopCartAction {
    public String getTotalPrice(HttpServletRequest request, HttpServletResponse response) {
        List<Book> shopCart = (List<Book>) request.getSession().getAttribute("shopcart");
        Float price = null;
        if (shopCart != null) {
            for (Book book : shopCart) {
                price += book.getPrice();
            }
        }
        request.setAttribute("totalPrice", price);
        return "showcart.jsp";
    }
}
