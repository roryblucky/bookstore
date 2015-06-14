package com.rory.bookstore.service.impl;

import com.rory.bookstore.dao.IUserDao;
import com.rory.bookstore.dao.impl.UserDaoImpl;
import com.rory.bookstore.domain.User;
import com.rory.bookstore.service.IUserService;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public class UserService implements IUserService {

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
    public List<User> findAll() {
        List<User> users = null;
        try {
            users = userDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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
}
