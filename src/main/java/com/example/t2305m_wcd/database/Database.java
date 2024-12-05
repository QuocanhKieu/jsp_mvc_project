//package com.example.t2305m_wcd.database;
//
//import java.sql.*;
//
//public class Database {
//    private final String connectionString = "jdbc:mysql://localhost:3306/t2305m_wcd";
//    private final String user = "root";
////    private final String password = "root";
//    private final String driver = "com.mysql.cj.jdbc.Driver";
//
//    // singleton pattern
//    private static Database _instance;
//    private Connection conn;
//    private Database(){
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(connectionString,user,"");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static Database createInstance(){
//        if(_instance == null)
//            _instance = new Database();
//        return _instance;
//    }
//
//    public Statement getStatement() throws SQLException {
//        return conn.createStatement();
//    }
//    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
//        return conn.prepareStatement(sql);
//    }
//}

package com.example.t2305m_wcd.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private final String connectionString = "jdbc:mysql://localhost:3306/t2305m_wcd";
    private final String user = "root";
    private final String password = "";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    // Singleton instance
    private static Database _instance;
    private Connection conn;

    private Database() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connectionString, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database createInstance() {
        if (_instance == null) {
            synchronized (Database.class) {
                if (_instance == null) {
                    _instance = new Database();
                }
            }
        }
        return _instance;
    }

    public Statement getStatement() throws SQLException {
        if (conn == null) {
            throw new SQLException("Database connection is not initialized.");
        }
        return conn.createStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if (conn == null) {
            throw new SQLException("Database connection is not initialized.");
        }
        return conn.prepareStatement(sql);
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
