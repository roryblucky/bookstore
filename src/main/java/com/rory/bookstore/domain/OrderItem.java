package com.rory.bookstore.domain;

/**
 * Created by RoryGao on 15/6/25.
 */
public class OrderItem {
    private String id;
    private Book book;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
