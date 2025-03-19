package com.example.springtodosubject.db;

import java.sql.*;

public class DBConnectionTest {
    public static void start() {
        String DB_URL = "jdbc:mysql://localhost:3306/todo?useUnicode=true&characterEncoding=utf8";
        String DB_USER = "root";
        String DB_PASSWORD = "596931";

        try {
            // DB 연결
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();

            String query = "SELECT now()";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String now = resultSet.getString(1);
                System.out.println("DB Connection Success!");
                System.out.println("현재 시간: " + now);
            }
        } catch (SQLException e) {
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Message: " + e.getMessage());
        }
    }
}
