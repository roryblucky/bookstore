package com.rory.bookstore.dao.impl;

import com.rory.bookstore.domain.Order;
import com.rory.bookstore.utils.BeanFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by RoryGao on 15/6/25.
 */
public class OrderDaoImplTest {

    @Test
    public void testFindById() throws Exception {
        Order order = BeanFactory.getInstance(OrderDaoImpl.class).findById("1");
        assertEquals(order.getCustomer().getName(), "淡定");
        assertNotNull(order.getOrderItems());
    }


    @Test
    public void testFindOrderTotalCount() throws Exception {
        int result = BeanFactory.getInstance(OrderDaoImpl.class).findOrderTotalCount();
        assertNotEquals(0, result);
    }


    @Test
    public void testAddOrder() throws Exception {

    }

    @Test
    public void testRemoveOrder() throws Exception {

    }

    @Test
    public void testUpdateOrder() throws Exception {

    }

    @Test
    public void testFindPageRecords() throws Exception {

    }
}