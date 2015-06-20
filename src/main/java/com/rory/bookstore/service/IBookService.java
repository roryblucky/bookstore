package com.rory.bookstore.service;

import com.rory.bookstore.domain.Book;
import com.rory.bookstore.web.bean.PageBean;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface IBookService {
    int addBook(Book book);

    int removeBook(String bookId);

    int updateBook(Book book);

    Book findById(String bookId);

    PageBean findBooks(String pageNum);

}
