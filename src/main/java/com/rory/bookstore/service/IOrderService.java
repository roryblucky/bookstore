package com.rory.bookstore.service;

import com.rory.bookstore.domain.Order;
import com.rory.bookstore.domain.User;
import com.rory.bookstore.web.bean.PageBean;

import java.util.List;

/**
 * Created by RoryGao on 15/6/26.
 */
public interface IOrderService {
    int addOrder(Order order);

    int removeOrder(String orderId);

    int updateOrder(Order order);

    Order findById(String orderId);

    PageBean findOrders(String pageNum);

    List<Order> findOrdersByUser(User user);
}
