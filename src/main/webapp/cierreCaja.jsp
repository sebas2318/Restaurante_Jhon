<%@page import="com.restaurante.modelo.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="com.restaurante.dao.PedidoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PedidoDAO dao = new PedidoDAO();
    double ventasBrutas = dao.obtenerGananciasBrutas();
    double gastos = dao.obtenerTotalGastos();
    double balanceNeto = ventasBrutas - gastos;
    List<Pedido> listaPedidos = dao.obtenerDetalleVentasDia();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Cierre de Caja</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .card-container { display: flex; gap: 20px; margin-bottom: 30px; }
        .card { padding: 20px; border-radius: 10px; flex: 1; text-align: center; color: white; }
        .bg-ventas { background-color: #28a745; }
        .bg-gastos { background-color: #dc3545; }
        .bg-neto { background-color: #007bff; }
        
        .form-gastos { 
            background: #f8f9fa; 
            padding: 20px; 
            border-radius: 8px; 
            border: 1px solid #dee2e6;
            margin-bottom: 30px;
        }
        
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #f2f2f2; }
        
        .btn { padding: 10px 15px; text-decoration: none; border-radius: 5px; cursor: pointer; border: none; }
        .btn-print { background: #6c757d; color: white; }
        .btn-back { background: #f8f9fa; color: #333; border: 1px solid #ccc; }
        
        @media print { .no-print { display: none; } }
    </style>
</head>
<body>

<h2>Informe de Cierre de Caja - <%= new java.util.Date() %></h2>

<div class="card-container">
    <div class="card bg-ventas">
        <strong>Ventas Totales</strong><br>
        <span style="font-size: 1.5em;">$<%= String.format("%.2f", ventasBrutas) %></span>
    </div>
    <div class="card bg-gastos">
        <strong>Gastos del Día</strong><br>
        <span style="font-size: 1.5em;">$<%= String.format("%.2f", gastos) %></span>
    </div>
    <div class="card bg-neto">
        <strong>Ganancia Neta</strong><br>
        <span style="font-size: 1.5em;">$<%= String.format("%.2f", balanceNeto) %></span>
    </div>
</div>

<div class="form-gastos no-print">
    <h3 style="margin-top: 0;">+ Registrar Gasto Nuevo</h3>
    <form action="GastoServlet" method="POST" style="display: flex; gap: 15px; align-items: flex-end;">
        <div style="flex: 2;">
            <label style="display:block; margin-bottom:5px;">Descripción del Gasto:</label>
            <input type="text" name="descripcion" placeholder="Ej: Compra de verdura" required style="width:100%; padding:8px; border-radius:4px; border:1px solid #ccc;">
        </div>
        <div style="flex: 1;">
            <label style="display:block; margin-bottom:5px;">Monto ($):</label>
            <input type="number" step="0.01" name="monto" placeholder="0.00" required style="width:100%; padding:8px; border-radius:4px; border:1px solid #ccc;">
        </div>
        <button type="submit" class="btn" style="background-color: #e74c3c; color: white;">Guardar Gasto</button>
    </form>
</div>

<h3>Detalle de Movimientos (Ventas)</h3>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Ubicación</th>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Monto</th>
            <th>Estado</th>
        </tr>
    </thead>
    <tbody>
        <% if(listaPedidos.isEmpty()) { %>
            <tr><td colspan="6" style="text-align:center;">No hay movimientos hoy.</td></tr>
        <% } else {
            for(Pedido p : listaPedidos) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td>Mesa <%= p.getMesa() %></td>
                <td><%= p.getNombreProducto() %></td>
                <td><%= p.getCantidad() %></td>
                <td>$<%= String.format("%.2f", p.getTotal()) %></td>
                <td>
                    <span style="color: <%= p.getEstado().equals("Pagado") ? "green" : "orange" %>">
                        <%= p.getEstado() %>
                    </span>
                </td>
            </tr>
        <%  } 
        } %>
    </tbody>
</table>

<br class="no-print">
<div class="no-print">
    <button onclick="window.print()" class="btn btn-print">Imprimir Informe</button>
    <a href="InventarioServlet" class="btn btn-back">Volver al Inventario</a>
</div>

</body>
</html>