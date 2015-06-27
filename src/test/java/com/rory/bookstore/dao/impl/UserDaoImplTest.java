package com.rory.bookstore.dao.impl;

import com.rory.bookstore.domain.User;
import com.rory.bookstore.utils.StringUtils;
import org.junit.Test;

/**
 * Created by RoryGao on 15/6/13.
 */
public class UserDaoImplTest {

    @Test
    public void testAddUser() throws Exception {
        User user = new User(StringUtils.getUUID(), "rory", "123", "18710856557", "帝都", "roryblucky@gmail.com", "123", false);
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.addUser(user);
    }

    @Test
    public void testRemoveUser() throws Exception {

    }

    @Test
    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {

    }
}