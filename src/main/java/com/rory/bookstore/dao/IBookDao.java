package com.rory.bookstore.dao;

import com.rory.bookstore.domain.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface IBookDao {
    int addBook(Book book) throws SQLException;

    int removeBook(String bookId) throws SQLException;

    int updateBook(Book book) throws SQLException;

    Book findById(String bookId) throws SQLException;

    List<Book> findPageRecords(int startIndex, int pageSize) throws SQLException;

    int findBookCount() throws SQLException;
}
