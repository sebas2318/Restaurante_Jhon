<%@page import="com.restaurante.modelo.Pedido"%>
<%@page import="com.restaurante.modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="com.restaurante.dao.PedidoDAO"%>
<%@page import="com.restaurante.dao.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Obtenemos la lista de los pedidos
    PedidoDAO dao = new PedidoDAO();
    List<Pedido> listaPedidos = dao.obtenerDetalleVentasDia();
    
    // Cargamos los productos para el menú desplegable
    ProductoDAO prodDao = new ProductoDAO();
    List<Producto> listaProds = prodDao.listarProductos();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Restaurante Jhon - Panel Mesero</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; display: flex; gap: 40px; }
        .form-container { width: 350px; border: 1px solid #ccc; padding: 20px; border-radius: 8px; height: fit-content; }
        .list-container { flex-grow: 1; }
        input { width: 93%; margin-bottom: 10px; padding: 8px; }
        label { font-weight: bold; display: block; margin-bottom: 5px; }
        button { width: 100%; padding: 10px; cursor: pointer; border: none; border-radius: 4px; }
        .btn-send { background: #28a745; color: white; }
        .btn-pay { background-color: #2ecc71; color: white; padding: 5px 10px; width: auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f8f9fa; }
        .estado-listo { color: #d35400; font-weight: bold; }
        .estado-pagado { color: #27ae60; }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Tomar Pedido</h2>
        <form action="PedidoServlet" method="POST">
            <input type="hidden" name="accion" value="registrar">
            
            <label>Mesa:</label>
            <input type="text" name="mesa" placeholder="Ej: Mesa 5" required>

            <label>Producto:</label>
        <select name="id_producto" required style="width: 100%; padding: 8px; margin-bottom: 10px;">
            <option value="">-- Seleccione un producto --</option>
            <% for(Producto p : listaProds) { %>
                <option value="<%= p.getId() %>">
                    <%= p.getNombre() %> - $<%= p.getPrecio() %> (Stock: <%= p.getStock() %>)
                </option>
            <% } %>
        </select>

            <label>Cantidad:</label>
            <input type="number" name="cantidad" value="1" min="1" required>

            <button type="submit" class="btn-send">Enviar a Cocina</button>
        </form>
        <hr>
        <p><a href="LoginServlet?logout=true" style="color: red;">Cerrar Sesión</a></p>
    </div>

    <div class="list-container">
        <h2>Pedidos de Hoy</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Mesa</th>
                    <th>Producto</th>
                    <th>Estado</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <% if(listaPedidos != null) { 
                    for(Pedido p : listaPedidos) { %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getMesa() %></td>
                        <td><%= p.getNombreProducto() %></td>
                        <td>
                            <span class="<%= p.getEstado().equals("Listo") ? "estado-listo" : "" %>">
                                <%= p.getEstado() %>
                            </span>
                        </td>
                        <td>
                            <% if ("Listo".equals(p.getEstado())) { %>
                                <form action="PedidoServlet" method="POST" style="margin:0;">
                                    <input type="hidden" name="accion" value="cambiarEstado">
                                    <input type="hidden" name="idPedido" value="<%= p.getId() %>">
                                    <input type="hidden" name="nuevoEstado" value="Pagado">
                                    <button type="submit" class="btn-pay">✔ Marcar Pagado</button>
                                </form>
                            <% } else if ("Pagado".equals(p.getEstado())) { %>
                                <span class="estado-pagado">✅ Cobrado</span>
                            <% } else { %>
                                <small>Esperando cocina...</small>
                            <% } %>
                        </td>
                    </tr>
                <%  } 
                } %>
            </tbody>
        </table>
    </div>

</body>
</html>