package com.rory.bookstore.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by RoryGao on 15/6/13.
 */
public class DBUtils {

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private static DataSource dataSource = null;

    static {
        Properties pro = new Properties();
        try {
            pro.load(DBUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Connection getConnection() {

        Connection conn = connectionThreadLocal.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                connectionThreadLocal.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static PreparedStatement getPreparedStatement(String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(sql);

            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    public static void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionThreadLocal.remove();
    }


}
