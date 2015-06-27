package com.rory.bookstore.service.impl;

import com.rory.bookstore.dao.IOrderDao;
import com.rory.bookstore.dao.impl.OrderDaoImpl;
import com.rory.bookstore.domain.Order;
import com.rory.bookstore.domain.User;
import com.rory.bookstore.service.IOrderService;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.StringUtils;
import com.rory.bookstore.web.bean.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RoryGao on 15/6/26.
 */
public class OrderServiceImpl implements IOrderService {

    private IOrderDao orderDao = BeanFactory.getInstance(OrderDaoImpl.class);

    @Override
    public int addOrder(Order order) {
        int result = -1;
        try {
            result = orderDao.addOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int removeOrder(String orderId) {
        int result = -1;
        try {
            result = orderDao.removeOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateOrder(Order order) {
        int result = -1;
        try {
            result = orderDao.updateOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Order findById(String orderId) {
        Order order = null;
        try {
            order = orderDao.findById(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public PageBean findOrders(String pageNum) {
        int currentPage = 1;
        if (!StringUtils.isEmpty(pageNum)) {
            currentPage = Integer.parseInt(pageNum);
        }
        PageBean pageBean = null;
        try {
            int totalCount = orderDao.findOrderTotalCount();
            pageBean = new PageBean(currentPage, totalCount);
            pageBean.setRecords(orderDao.findPageRecords(pageBean.getStartIndex(), pageBean.getPageSize()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pageBean;
    }

    @Override
    public List<Order> findOrdersByUser(User user) {
        List<Order> orders = null;

        try {
            orders = orderDao.findOrdersByUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
