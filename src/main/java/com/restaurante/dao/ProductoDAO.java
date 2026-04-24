package com.restaurante.dao;

import com.restaurante.conexion.Conexion;
import com.restaurante.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductoDAO {
    private Conexion conexion = new Conexion();

    public boolean registrar(Producto p) {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
        
        try (Connection con = conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
       
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al guardar: " + e.getMessage());
            return false;
        }
    }

    public void listarProductos() {
        String sql = "SELECT * FROM productos";
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            System.out.println("\n--- INVENTARIO DE PRODUCTOS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Nombre: " + rs.getString("nombre") + 
                                   " | Precio: $" + rs.getDouble("precio") + 
                                   " | Stock: " + rs.getInt("stock"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar: " + e.getMessage());
        }
    }
}