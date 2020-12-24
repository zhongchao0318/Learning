package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DBUtil {
    private static final String URL = "jdbc:oracle:thin:@200.4.20.70:1521:OA";
    private static final String USER = "ChengFengWang";
    private static final String PASS = "Cfw0099Pqr117";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("-----数据库连接成功------");
            log.info("sss");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection conn, Statement state, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (state != null) state.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
