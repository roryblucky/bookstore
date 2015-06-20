package com.rory.bookstore.service;

import com.rory.bookstore.domain.User;

/**
 * Created by RoryGao on 15/6/13.
 */
public interface IUserService {
    int addUser(User user);

    int removeUser(String userId);

    int updateUser(User user);

    User findById(String id);

    User findByCode(String name, String verifyCode);

    User findUser(String name, String password);
}
