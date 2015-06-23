package com.rory.bookstore.dao;

import com.rory.bookstore.domain.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RoryGao on 2015/6/23.
 */
public interface IOrderDao {
    int addOrder(Order order) throws SQLException;

    int removeOrder(String orderId) throws SQLException;

    int updateOrder(Order order) throws SQLException;

    Order findById(String orderId) throws SQLException;

    List<Order> findPageRecords(int startIndex, int pageSize) throws SQLException;

    int findOrderTotalCount() throws SQLException;
}
