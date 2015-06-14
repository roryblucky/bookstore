package com.rory.bookstore.service;

import com.rory.bookstore.domain.Category;

import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface ICategoryService {
    int addCategory(Category category);

    int removeCategory(String categoryId);

    int updateCategory(Category category);

    List<Category> findAll();

    Category findById(String categoryId);
}
