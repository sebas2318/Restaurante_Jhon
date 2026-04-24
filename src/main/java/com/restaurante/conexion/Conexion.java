package com.restaurante.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/restaurantejk";
    private static final String USER = "root";
    private static final String PASS = "";     

    public Connection conectar() {
    Connection con = null;
    try {
        // Esta línea le dice a Java: "Usa el traductor de MySQL"
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        
        con = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("✅ ¡Conexión exitosa!");
    } catch (ClassNotFoundException e) {
        System.out.println("❌ No se encontró el Driver: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("❌ Error: " + e.getMessage());
    }
    return con;
}
}