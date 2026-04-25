package com.restaurante.dao;

import com.restaurante.conexion.Hash;
import com.restaurante.conexion.Conexion;
import com.restaurante.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public String validarUsuario(String user, String pass) {
    String rol = null;
    String passConHash = com.restaurante.conexion.Hash.sha256(pass); 
    
    // ESTO ES PARA DEPURAR (Mira la consola de NetBeans al intentar entrar)
    System.out.println("USER INGRESADO: " + user);
    System.out.println("HASH GENERADO: " + passConHash);

    String sql = "SELECT rol FROM usuarios WHERE username = ? AND password = ?";
    
    try (Connection con = new Conexion().getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, user);
        ps.setString(2, passConHash);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                rol = rs.getString("rol");
                System.out.println("LOG: Usuario encontrado, Rol: " + rol);
            } else {
                System.out.println("LOG: No se encontró coincidencia en la DB.");
            }
        }
    } catch (Exception e) {
        System.err.println("ERROR EN DAO: " + e.getMessage());
    }
    return rol;
}
}