package com.rory.bookstore.dao;

import com.rory.bookstore.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface IUserDao {
    int addUser(User user) throws SQLException;

    int removeUser(String userId) throws SQLException;

    int updateUser(User user) throws SQLException;

    List<User> findAll() throws SQLException;

    User findById(String id) throws SQLException;
}
