package com.rory.bookstore.dao.impl;

import com.rory.bookstore.dao.IUserDao;
import com.rory.bookstore.domain.User;
import com.rory.bookstore.utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public int addUser(User user) throws SQLException {
        final String sql = "insert into tb_user values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql,
                user.getId(), user.getName(), user.getPwd(),
                user.getPhoneNum(), user.getEmailAddress(), user.getVerifyCode(),
                false);
        int result = preparedStatement.executeUpdate();
        DBUtils.closeConnection();
        return result;
    }

    @Override
    public int removeUser(String userId) throws SQLException {
        final String sql = "delete from tb_user where id = ?";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql, userId);
        int result = preparedStatement.executeUpdate();
        DBUtils.closeConnection();
        return result;
    }

    @Override
    public int updateUser(User user) throws SQLException {
        final String sql = "update tb_user set " +
                "user_name=?, user_pwd=?, user_phone_num=?, email_address=? where id = ?";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql,
                user.getName(), user.getPwd(), user.getPhoneNum(), user.getEmailAddress(), user.getId());
        int result = preparedStatement.executeUpdate();
        DBUtils.closeConnection();
        return result;
    }

    @Override
    public List<User> findAll() throws SQLException {
        final String sql = "select * from tb_user";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        List<User> users = new LinkedList<User>();
        if (rs != null) {
            while (rs.next()) {
                User user = new User(rs.getString("id"), rs.getString("user_name"), rs.getString("user_pwd"),
                        rs.getString("user_phone_num"), rs.getString("email_address"),
                        rs.getString("verify_code"), rs.getBoolean("isActive"));
                users.add(user);
            }
        }
        rs.close();
        DBUtils.closeConnection();
        return users;
    }

    @Override
    public User findById(String id) throws SQLException {
        final String sql = "select * from tb_user where id = ?";
        PreparedStatement preparedStatement = DBUtils.getPreparedStatement(sql, id);
        ResultSet rs = preparedStatement.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("id"), rs.getString("user_name"), rs.getString("user_pwd"),
                    rs.getString("user_phone_num"), rs.getString("email_address"),
                    rs.getString("verify_code"), rs.getBoolean("isActive"));
        }
        rs.close();
        DBUtils.closeConnection();
        return user;
    }
}
