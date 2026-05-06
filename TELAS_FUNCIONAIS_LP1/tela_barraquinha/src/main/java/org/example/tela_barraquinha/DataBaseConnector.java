package org.example.tela_barraquinha;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/barraquinha";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}