<%@page import="com.restaurante.modelo.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="com.restaurante.dao.PedidoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Panel de Cocina - Restaurante JK</title>
    <style>
        body { font-family: Arial; margin: 20px; background-color: #f8f9fa; }
        .pedido-card { background: white; border-left: 5px solid #ffc107; padding: 15px; margin-bottom: 10px; border-radius: 4px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); display: flex; justify-content: space-between; align-items: center;}
        .btn-listo { background: #28a745; color: white; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h1>👨‍🍳 Pedidos Pendientes en Cocina</h1>
    <div id="contenedor-pedidos">
    <%
        com.restaurante.dao.PedidoDAO daoPedido = new com.restaurante.dao.PedidoDAO();
        List<Pedido> pendientes = daoPedido.listarPedidosPendientes();
        
        if (pendientes == null || pendientes.isEmpty()) {
    %>
        <h3 style="color: gray; text-align: center;">No hay pedidos por preparar en este momento.</h3>
    <%
        } else {
            for(Pedido p : pendientes) {

    %>
        <div class="pedido-card">
            <strong>Mesa: <%= p.getMesa() %></strong> - 
            <%= p.getNombreProducto() %> (Cant: <%= p.getCantidad() %>)
            <form action="CocinaServlet" method="POST" style="display:inline;">
                <input type="hidden" name="idPedido" value="<%= p.getId() %>">
                <button type="submit" class="btn-listo">Listo</button>
            </form>
        </div>
    <% 
            }
        } 
    %>
</div>
    <p><a href="LoginServlet?logout=true">Cerrar Sesión</a></p>
</body>
</html>