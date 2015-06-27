package com.rory.bookstore.dao.impl;

import com.rory.bookstore.dao.IUserDao;
import com.rory.bookstore.domain.User;
import com.rory.bookstore.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by RoryGao on 15/6/13.
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public int addUser(User user) throws SQLException {
        final String sql = "insert into tb_user values(?,?,?,?,?,?,?)";
        return DBUtils.executeUpdate(sql,
                user.getId(), user.getName(), user.getPwd(),
                user.getPhoneNum(), user.getEmailAddress(), user.getVerifyCode(),
                false);
    }

    @Override
    public int removeUser(String userId) throws SQLException {
        final String sql = "delete from tb_user where id = ?";
        return DBUtils.executeUpdate(sql, userId);
    }

    @Override
    public int updateUser(User user) throws SQLException {
        final String sql = "update tb_user set " +
                "user_name=?, user_pwd=?, user_phone_num=?, email_address=?, verify_code=?, isActive=? where id = ?";
        return DBUtils.executeUpdate(sql,
                user.getName(), user.getPwd(), user.getPhoneNum(), user.getEmailAddress(),
                user.getVerifyCode(), user.isActive(), user.getId());
    }

    @Override
    public User findById(String id) throws SQLException {
        final String sql = "select * from tb_user where id = ?";
        ResultSet rs = DBUtils.executeQuery(sql, id);
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("id"), rs.getString("user_name"), rs.getString("user_pwd"),
                    rs.getString("user_phone_num"), rs.getString("user_address"), rs.getString("email_address"),
                    rs.getString("verify_code"), rs.getBoolean("isActive"));
        }
        rs.close();
        DBUtils.closeConnection();
        return user;
    }

    @Override
    public User findByCode(String name, String verifyCode) throws SQLException {
        final String sql = "select * from tb_user where user_name = ? and verify_code = ?";
        ResultSet rs = DBUtils.executeQuery(sql, name, verifyCode);
        User user = null;

        if (rs.next()) {
            user = new User(rs.getString("id"), rs.getString("user_name"), rs.getString("user_pwd"),
                    rs.getString("user_phone_num"), rs.getString("user_address"), rs.getString("email_address"),
                    rs.getString("verify_code"), rs.getBoolean("isActive"));
        }
        rs.close();
        DBUtils.closeConnection();
        return user;
    }

    @Override
    public User findUser(String name, String password) throws SQLException {
        final String sql = "select * from tb_user where user_name = ? and user_pwd = ?";
        ResultSet rs = DBUtils.executeQuery(sql, name, password);
        User user = null;

        if (rs.next()) {
            user = new User(rs.getString("id"), rs.getString("user_name"), rs.getString("user_pwd"),
                    rs.getString("user_phone_num"), rs.getString("user_address"), rs.getString("email_address"),
                    rs.getString("verify_code"), rs.getBoolean("isActive"));
        }
        rs.close();
        DBUtils.closeConnection();
        return user;
    }
}
