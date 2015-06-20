package com.rory.bookstore.dao.impl;

import com.rory.bookstore.dao.ICategoryDao;
import com.rory.bookstore.domain.Category;
import com.rory.bookstore.utils.DBUtils;

import java.sql.PreparedStatement;
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
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql,
                category.getId(), category.getName(), category.getDescription());
        int result = preparedStatement.executeUpdate();
        DBUtils.closeConnection();
        return result;
    }

    @Override
    public int removeCategory(String categoryId) throws SQLException {
        final String sql = "delete from tb_category where id = ?";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql, categoryId);
        int result = preparedStatement.executeUpdate();
        DBUtils.closeConnection();
        return result;
    }

    @Override
    public int updateCategory(Category category) throws SQLException {
        final String sql = "update tb_category set name = ?, description = ? where id = ?";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql,
                category.getName(), category.getDescription(), category.getId());
        int result = preparedStatement.executeUpdate();
        DBUtils.closeConnection();
        return result;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        final String sql = "select * from tb_category";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        List<Category> categories = new LinkedList<>();
        if (rs != null) {
            while (rs.next()) {
                Category category = new Category(rs.getString("id"), rs.getString("name"), rs.getString("description"));
                categories.add(category);
            }
        }
        DBUtils.closeConnection();
        return categories;
    }

    @Override
    public Category findById(String categoryId) throws SQLException {
        final String sql = "select * from tb_category where id = ?";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql, categoryId);
        ResultSet rs = preparedStatement.executeQuery();
        Category category = null;
        if (rs.next()) {
            category = new Category(rs.getString("id"), rs.getString("name"), rs.getString("description"));
        }
        DBUtils.closeConnection();
        return category;
    }
}
