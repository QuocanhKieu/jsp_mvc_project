package com.example.t2305m_wcd.database;

import java.sql.*;

public class Database {
    private final String connectionString = "jdbc:mysql://localhost:3306/t2305m_wcd";
    private final String user = "root";
//    private final String password = "root";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    // singleton pattern
    private static Database _instance;
    private Connection conn;
    private Database(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connectionString,user,"");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Database createInstance(){
        if(_instance == null)
            _instance = new Database();
        return _instance;
    }

    public Statement getStatement() throws SQLException {
        return conn.createStatement();
    }
    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
        return conn.prepareStatement(sql);
    }
}
