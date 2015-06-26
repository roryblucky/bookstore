package com.rory.bookstore.dao.impl;

import com.rory.bookstore.dao.IOrderDao;
import com.rory.bookstore.domain.*;
import com.rory.bookstore.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by RoryGao on 15/6/25.
 */
public class OrderDaoImpl implements IOrderDao {
    @Override
    public int addOrder(Order order) throws SQLException {
        final String sql = "insert into tb_order values(?,?,?,?)";
        int orderResult = DBUtils.executeUpdate(sql, order.getId(), order.getTotalPrice(),
                order.getStatus(), order.getCustomer().getId());

        final String orderItemSql = "insert into tb_orderItem values(?,?,?)";

        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            DBUtils.executeUpdate(orderItemSql, orderItem.getId(), orderItem.getBook().getId(), order.getId());
        }

        return orderResult;
    }

    @Override
    public int removeOrder(String orderId) throws SQLException {
        final String sql = "delete from tb_order where id = ?";
        return DBUtils.executeUpdate(sql, orderId);
    }

    @Override
    public int updateOrder(Order order) throws SQLException {
        final String sql = "update tb_order set status = ? where id = ?";
        return DBUtils.executeUpdate(sql, order.getStatus(), order.getId());
    }

    @Override
    public Order findById(String orderId) throws SQLException {
        List<OrderItem> orderItems = getOrderItems(orderId);

        final String orderSql = "SELECT" +
                "  o.total_price    AS orderPrice," +
                "  o.status         AS orderStatus," +
                "  u.user_name      AS userName," +
                "  u.user_phone_num AS userPhone," +
                "  u.email_address  AS email" +
                " FROM tb_order o" +
                "  INNER JOIN tb_orderItem i ON o.id = i.order_id" +
                "  INNER JOIN tb_user u ON u.id = o.customer_id" +
                " WHERE i.order_id = ?";
        ResultSet orderRs = DBUtils.executeQuery(orderSql, orderId);

        Order order = null;
        if (orderRs.next()) {
            User customer = new User();
            customer.setName(orderRs.getString("userName"));
            customer.setPhoneNum(orderRs.getString("userPhone"));
            customer.setEmailAddress(orderRs.getString("email"));
            order = new Order();
            order.setId(orderId);
            order.setTotalPrice(orderRs.getFloat("orderPrice"));
            order.setStatus(orderRs.getInt("orderStatus"));
            order.setCustomer(customer);
            order.setOrderItems(orderItems);
        }
        return order;
    }

    private List<OrderItem> getOrderItems(String orderId) throws SQLException {
        final String orderItemSql = "SELECT" +
                "  b.id           AS bookId," +
                "  b.name         AS bookName," +
                "  b.author       AS bookAuthor," +
                "  b.price        AS bookPrice," +
                "  b.picture_path AS bookPic," +
                "  b.description  AS bookDesc," +
                "  c.id           AS categoryId," +
                "  c.name         AS categoryName," +
                "  c.description  AS categoryDesc," +
                "  i.id           AS itemId" +
                " FROM tb_book b" +
                "  INNER JOIN tb_category c ON c.id = b.category_id" +
                "  INNER JOIN tb_orderItem i ON b.id = i.book_id" +
                " WHERE i.order_id = ?";
        ResultSet orderItemRs = DBUtils.executeQuery(orderItemSql, orderId);

        List<OrderItem> orderItems = new LinkedList<>();
        while (orderItemRs.next()) {
            Category category = new Category(orderItemRs.getString("categoryId"), orderItemRs.getString("categoryName"),
                    orderItemRs.getString("categoryDesc"));
            Book book = new Book(orderItemRs.getString("bookId"), orderItemRs.getString("bookName"),
                    orderItemRs.getString("bookAuthor"), orderItemRs.getFloat("bookPrice"),
                    orderItemRs.getString("bookPic"), category, orderItemRs.getString("bookDesc"));
            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemRs.getString("itemId"));
            orderItem.setBook(book);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    @Override
    public List<Order> findPageRecords(int startIndex, int pageSize) throws SQLException {
        final String orderSql = "SELECT" +
                "  o.id             AS orderId," +
                "  o.total_price    AS orderPrice," +
                "  o.status         AS orderStatus," +
                "  u.user_name      AS userName," +
                "  u.user_phone_num AS userPhone," +
                "  u.email_address  AS email" +
                " FROM tb_order o" +
                "  INNER JOIN tb_orderItem i ON o.id = i.order_id" +
                "  INNER JOIN tb_user u ON u.id = o.customer_id" +
                " LIMIT ? ?";
        ResultSet orderRs = DBUtils.executeQuery(orderSql, startIndex, pageSize);
        List<Order> orders = new ArrayList<>();
        while (orderRs.next()) {
            Order order = new Order();
            User customer = new User();
            customer.setName(orderRs.getString("userName"));
            customer.setPhoneNum(orderRs.getString("userPhone"));
            customer.setEmailAddress(orderRs.getString("email"));
            order.setId(orderRs.getString("orderId"));
            order.setTotalPrice(orderRs.getFloat("orderPrice"));
            order.setStatus(orderRs.getInt("orderStatus"));
            order.setCustomer(customer);
            List<OrderItem> orderItems = this.getOrderItems(order.getId());
            order.setOrderItems(orderItems);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public int findOrderTotalCount() throws SQLException {
        final String sql = "select count(*) as totalCount from tb_order";
        ResultSet rs = DBUtils.executeQuery(sql);
        if (rs.next()) {
            return Integer.parseInt(rs.getString("totalCount"));
        }
        return 0;
    }
}
