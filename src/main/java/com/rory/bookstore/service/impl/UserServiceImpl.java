package com.rory.bookstore.service.impl;

import com.rory.bookstore.dao.IUserDao;
import com.rory.bookstore.dao.impl.UserDaoImpl;
import com.rory.bookstore.domain.User;
import com.rory.bookstore.service.IUserService;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.StringUtils;

import java.sql.SQLException;

/**
 * Created by RoryGao on 15/6/13.
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao = BeanFactory.getInstance(UserDaoImpl.class);

    @Override
    public int addUser(User user) {
        int result = -1;
        try {
            user.setId(StringUtils.getUUID());
            result = userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int removeUser(String userId) {
        int result = -1;
        try {
            result = userDao.removeUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateUser(User user) {
        int result = -1;
        try {
            result = userDao.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(String id) {
        User user = null;
        try {
            user = userDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByCode(String name, String verifyCode) {
        User user = null;

        try {
            user = userDao.findByCode(name, verifyCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findUser(String name, String password) {
        User user = null;

        try {
            user = userDao.findUser(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
