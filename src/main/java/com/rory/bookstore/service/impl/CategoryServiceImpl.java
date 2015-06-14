package com.rory.bookstore.service.impl;

import com.rory.bookstore.dao.ICategoryDao;
import com.rory.bookstore.dao.impl.CategoryDaoImpl;
import com.rory.bookstore.domain.Category;
import com.rory.bookstore.service.ICategoryService;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public class CategoryServiceImpl implements ICategoryService {

    private ICategoryDao categoryDao = BeanFactory.getInstance(CategoryDaoImpl.class);

    @Override
    public int addCategory(Category category) {
        int result = -1;
        try {
            category.setId(StringUtils.getUUID());
            result = categoryDao.addCategory(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int removeCategory(String categoryId) {
        int result = -1;
        try {
            result = categoryDao.removeCategory(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateCategory(Category category) {
        int result = -1;
        try {
            result = categoryDao.updateCategory(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = null;
        try {
            categories = categoryDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(String categoryId) {
        Category category = null;
        try {
            category = categoryDao.findById(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
