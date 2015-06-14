package com.rory.bookstore.service.impl;

import com.rory.bookstore.dao.IBookDao;
import com.rory.bookstore.dao.impl.BookDaoImpl;
import com.rory.bookstore.domain.Book;
import com.rory.bookstore.service.IBookService;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public class BookServiceImpl implements IBookService {

    private IBookDao bookDao = BeanFactory.getInstance(BookDaoImpl.class);

    @Override
    public int addBook(Book book) {
        int result = -1;
        try {
            book.setId(StringUtils.getUUID());
            result = bookDao.addBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int removeBook(String bookId) {
        int result = -1;
        try {
            result = bookDao.removeBook(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateBook(Book book) {
        int result = -1;
        try {
            result = bookDao.updateBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = null;
        try {
            books = bookDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book findById(String bookId) {
        Book book = null;
        try {
            book = bookDao.findById(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }


}
