/**
 * Clase ProductoDAO: Implementa el patrón Data Access Object.
 */
package com.restaurante.dao;

import com.restaurante.conexion.Conexion;
import com.restaurante.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    /**
     *RF01: Tomar Pedido.
     *Mesero solicita productos y descuenta stock.
     */
    public boolean descontarStock(int idProducto, int cantidad, String mesa) {
        String sqlUpdate = "UPDATE productos SET stock = stock - ? WHERE id = ? AND stock >= ?";
        String sqlInsert = "INSERT INTO pedido (mesa, id_producto, cantidad) VALUES (?, ?, ?)";
        
        Connection con = null;
        try {
            con = new Conexion().getConexion();
            con.setAutoCommit(false);

            try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                psUpdate.setInt(1, cantidad);
                psUpdate.setInt(2, idProducto);
                psUpdate.setInt(3, cantidad);
                
                int filasActualizadas = psUpdate.executeUpdate();
                
                if (filasActualizadas > 0) {
                    try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
                        psInsert.setString(1, mesa);
                        psInsert.setInt(2, idProducto);
                        psInsert.setInt(3, cantidad);
                        psInsert.executeUpdate();
                    }
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }
            }
        } catch (Exception e) {
            try { if (con != null) con.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
     /**
     *RF05: Registrar Producto.
     * Administrador registra un nuevo producto a el inventario
     */
    public boolean guardarProducto(Producto p) {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     *RF06: Ver inventario.
     * Administrador podra ver y gestionar el inventario.
     */
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}