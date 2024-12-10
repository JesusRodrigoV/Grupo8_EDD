package com.farmacia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:postgresql://localhost:5432/db_farmacy";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexión exitosa. Desde aqui");
            } else {
                System.out.println("No se pudo conexión.");
            }
        } catch (SQLException e) {
            System.err.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }
}
