package com.restaurante.dao;

import com.restaurante.conexion.Conexion;
import com.restaurante.modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // 1. Listar pedidos para la cocina
    public List<Pedido> listarPedidosPendientes() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.mesa, IFNULL(pr.nombre, 'PRODUCTO NO ENCONTRADO') as producto_nombre, " +
                     "p.cantidad, p.estado " +
                     "FROM pedido p " +
                     "LEFT JOIN productos pr ON p.id_producto = pr.id " +
                     "WHERE p.estado = 'Pendiente'";
        
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId(rs.getInt("id"));
                ped.setMesa(rs.getString("mesa"));
                ped.setNombreProducto(rs.getString("producto_nombre"));
                ped.setCantidad(rs.getInt("cantidad"));
                ped.setEstado(rs.getString("estado"));
                lista.add(ped);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 2. Actualizar estado del pedido
    public boolean actualizarEstadoPedido(int idPedido, String nuevoEstado) {
        String sql = "UPDATE pedido SET estado = ? WHERE id = ?";
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idPedido);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { 
            e.printStackTrace(); 
            return false; 
        }
    }

    // 3. Registrar un gasto nuevo
    public boolean registrarGasto(String descripcion, double monto) {
        String sql = "INSERT INTO gastos (descripcion, monto, fecha) VALUES (?, ?, CURRENT_TIMESTAMP)";
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, descripcion);
            ps.setDouble(2, monto);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Obtener Ganancias (Suma de pedidos pagados hoy)
    public double obtenerGananciasBrutas() {
        double total = 0;
        String sql = "SELECT SUM(total) as total_dia FROM pedido WHERE estado = 'Pagado' AND DATE(fecha) = CURDATE()";
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("total_dia");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // 5. Obtener Total de Gastos del día
    public double obtenerTotalGastos() {
        double totalGastos = 0;
        String sql = "SELECT SUM(monto) as total_g FROM gastos WHERE DATE(fecha) = CURDATE()";
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalGastos = rs.getDouble("total_g");
            }
        } catch (Exception e) {
            System.err.println("Error en gastos: " + e.getMessage());
        }
        return totalGastos;
    }

    // 6. Detalle completo para la tabla del informe
    public List<Pedido> obtenerDetalleVentasDia() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.mesa, p.cantidad, p.total, p.estado, IFNULL(pr.nombre, 'Servicio') as producto_nombre " +
                     "FROM pedido p " +
                     "LEFT JOIN productos pr ON p.id_producto = pr.id " +
                     "WHERE DATE(p.fecha) = CURDATE()";
        
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId(rs.getInt("id"));
                ped.setMesa(rs.getString("mesa"));
                ped.setCantidad(rs.getInt("cantidad"));
                ped.setTotal(rs.getDouble("total"));
                ped.setEstado(rs.getString("estado"));
                ped.setNombreProducto(rs.getString("producto_nombre"));
                lista.add(ped);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 7. Registrar pedido nuevo calculando el total automáticamente
    public boolean registrarPedido(Pedido p) {
        String sql ="INSERT INTO pedido (mesa, id_producto, cantidad, total, fecha, estado) " +
                   "SELECT ?, ?, ?, (precio * ?), CURRENT_TIMESTAMP, 'Pendiente' " +
                   "FROM productos WHERE id = ?";;
        
        try (Connection con = new Conexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getMesa());
            ps.setInt(2, p.getIdProducto());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCantidad()); 
            ps.setInt(5, p.getIdProducto()); 
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}