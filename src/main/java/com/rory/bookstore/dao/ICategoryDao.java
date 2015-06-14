package com.rory.bookstore.dao;

import com.rory.bookstore.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface ICategoryDao {
    int addCategory(Category category) throws SQLException;

    int removeCategory(String categoryId) throws SQLException;

    int updateCategory(Category category) throws SQLException;

    List<Category> findAll() throws SQLException;

    Category findById(String categoryId) throws SQLException;
}
