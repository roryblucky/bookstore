package com.rory.bookstore.service;

import com.rory.bookstore.domain.Book;

import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface IBookService {
    int addBook(Book book);

    int removeBook(String bookId);

    int updateBook(Book book);

    List<Book> findAll();

    Book findById(String bookId);

}
