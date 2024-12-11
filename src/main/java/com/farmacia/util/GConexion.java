package com.farmacia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class GConexion {
    public static Connection connection = null;

    public static final String usuario = "postgres";
    public static final String contraseña = "62625054"; // Actualiza según sea necesario
    public static final String bd = "dc_pharmacy"; // Cambia al nombre de tu base de datos
    public static final String ip = "localhost";
    public static final String puerto = "5432";

    public static final String cadena = "jdbc:postgresql://" + ip + ":" + puerto + "/" + bd;

    public static Connection establecerConexion() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(cadena, usuario, contraseña);
                JOptionPane.showMessageDialog(null, "Se conectó correctamente a la base de datos");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos, error: " + e.toString());
            }
        }
        return connection;
    }
}
