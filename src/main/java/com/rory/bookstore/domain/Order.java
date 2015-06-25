package com.rory.bookstore.domain;

import java.util.List;

/**
 * Created by RoryGao on 2015/6/23.
 */
public class Order {
    private String id;
    private Float totalPrice;
    private int status;//0未发货 1 发货 2 确认收货
    private User customer;
    private List<OrderItem> orderItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", customer=" + customer +
                ", orderItems=" + orderItems +
                '}';
    }
}
