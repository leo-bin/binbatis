package com.bins.binbatis.util;

import com.bins.binbatis.config.Configuration;

import java.sql.*;

/**
 * @author leo-bin
 * @date 2020/5/8 23:20
 * @apiNote 封装jdbc的工具包
 */
public class JdbcUtil {

    /**
     * jdbc基础配置信息
     */
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    /**
     * 连接实例，单例
     */
    private static volatile Connection connection;


    /**
     * 初始化
     */
    public static void init(Configuration config) {
        if (config != null) {
            driver = config.getJdbcDriver();
            url = config.getJdbcUrl();
            username = config.getJdbcUserName();
            password = config.getJdbcPassWord();
        } else {
            System.out.println("无法读取jdbc配置文件");
        }
    }


    /**
     * 获取connection
     */
    public static Connection getConnection() {
        //1.提前加载一次驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.获取连接实例
        singletonInit();
        return connection;
    }


    /**
     * 使用DCL模式保证connection是单例且线程安全的
     */
    private static void singletonInit() {
        if (connection == null) {
            synchronized (JdbcUtil.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(url, username, password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 提供close方法, 用于释放资源
     */
    public static void close(Connection conn, Statement stat,
                             ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stat = null;
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }
}
