package com.rory.bookstore.dao;

import com.rory.bookstore.domain.OrderItem;

import java.util.List;

/**
 * Created by RoryGao on 2015/6/23.
 */
public interface IOrderItemDao {
    int addOrderItem(OrderItem orderItem);

    List<OrderItem> findAll() throws Exception;
}
