package com.rory.bookstore.service;

import com.rory.bookstore.domain.User;

import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface IUserService {
    int addUser(User user);

    int removeUser(String userId);

    int updateUser(User user);

    List<User> findAll();

    User findById(String id);
}
