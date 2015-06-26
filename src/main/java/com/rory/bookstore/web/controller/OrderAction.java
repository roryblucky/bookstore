package com.rory.bookstore.web.controller;

import com.rory.bookstore.domain.Book;
import com.rory.bookstore.domain.Order;
import com.rory.bookstore.domain.OrderItem;
import com.rory.bookstore.domain.User;
import com.rory.bookstore.service.IOrderService;
import com.rory.bookstore.service.impl.OrderServiceImpl;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by RoryGao on 15/6/26.
 */
public class OrderAction {

    private IOrderService service = BeanFactory.getInstance(OrderServiceImpl.class);

    public String commitBill(HttpServletRequest request, HttpServletResponse response) {
        String totalPrice = request.getParameter("totalPrice");

        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) request.getSession().getAttribute("shopcart");

        User customer = (User) request.getSession().getAttribute("user");

        if (customer != null && !StringUtils.isEmpty(totalPrice)) {

            List<OrderItem> orderItems = new LinkedList<>();
            books.forEach((book) -> {
                OrderItem item = new OrderItem(StringUtils.getUUID(), book);
                orderItems.add(item);
            });

            Order order = new Order(StringUtils.getUUID(), Float.parseFloat(totalPrice), 0, customer, orderItems);
            int result = service.addOrder(order);
            if (result != -1) {
                request.setAttribute("msg", "提交成功，请耐心等待");
                request.getSession().removeAttribute("shopcart");
                return "result/success.jsp";
            } else {
                request.setAttribute("errorMsg", "系统忙");
                return "result/error.jsp";
            }

        } else {
            request.setAttribute("errorMsg", "用户未登录");
            return "result/error.jsp";
        }
    }
}
