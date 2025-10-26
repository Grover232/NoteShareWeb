package com.mycompany.noteshareweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/NoteShareWeb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "noteshareuser";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            // 👇 Forzar la carga del driver (esto soluciona el “No suitable driver”)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
