package com.rory.bookstore.dao.impl;

import com.rory.bookstore.dao.ICategoryDao;
import com.rory.bookstore.domain.Category;
import com.rory.bookstore.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public class CategoryDaoImpl implements ICategoryDao {


    @Override
    public int addCategory(Category category) throws SQLException {
        final String sql = "insert into tb_category values(?,?,?)";
        return DBUtils.executeUpdate(sql,
                category.getId(), category.getName(), category.getDescription());
    }

    @Override
    public int removeCategory(String categoryId) throws SQLException {
        final String sql = "delete from tb_category where id = ?";
        return DBUtils.executeUpdate(sql, categoryId);
    }

    @Override
    public int updateCategory(Category category) throws SQLException {
        final String sql = "update tb_category set name = ?, description = ? where id = ?";
        return DBUtils.executeUpdate(sql,
                category.getName(), category.getDescription(), category.getId());
    }

    @Override
    public List<Category> findAll() throws SQLException {
        final String sql = "select * from tb_category";
        ResultSet rs = DBUtils.executeQuery(sql);
        List<Category> categories = new LinkedList<>();
        if (rs != null) {
            while (rs.next()) {
                Category category = new Category(rs.getString("id"), rs.getString("name"), rs.getString("description"));
                categories.add(category);
            }
        }
        rs.close();
        DBUtils.closeConnection();
        return categories;
    }

    @Override
    public Category findById(String categoryId) throws SQLException {
        final String sql = "select * from tb_category where id = ?";
        ResultSet rs = DBUtils.executeQuery(sql, categoryId);
        Category category = null;
        if (rs.next()) {
            category = new Category(rs.getString("id"), rs.getString("name"), rs.getString("description"));
        }
        rs.close();
        DBUtils.closeConnection();
        return category;
    }
}
