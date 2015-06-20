package com.rory.bookstore.dao;

import com.rory.bookstore.domain.User;

import java.sql.SQLException;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface IUserDao {
    int addUser(User user) throws SQLException;

    int removeUser(String userId) throws SQLException;

    int updateUser(User user) throws SQLException;

    User findById(String id) throws SQLException;

    User findByCode(String name, String verifyCode) throws SQLException;

    User findUser(String name, String password) throws SQLException;
}
