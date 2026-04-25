package com.restaurante.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String baseDatos = "restaurantejk"; // Asegúrate que coincida con tu DB
    private final String url = "jdbc:mysql://localhost:3306/" + baseDatos + "?serverTimezone=UTC";
    private final String usuario = "root";
    private final String clave = "";

    public Connection getConexion() {
        Connection cn = null;
        try {
            // Carga del driver necesario para aplicaciones web PHP/Java 
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
        return cn;
    }
}