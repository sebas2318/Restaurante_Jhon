<%@page import="com.restaurante.modelo.Producto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Inventario - Restaurante de Jhon</title>
    <style>
        table { width: 80%; border-collapse: collapse; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .low-stock { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2 style="text-align: center;">Control de Inventario (Administrador)</h2>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Stock Actual</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Producto> lista = (List<Producto>) request.getAttribute("listaProductos");
                if (lista != null) {
                    for (Producto p : lista) {
            %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getNombre() %></td>
                <td>$<%= p.getPrecio() %></td>
                <td class="<%= p.getStock() < 5 ? "low-stock" : "" %>">
                    <%= p.getStock() %>
                </td>
            </tr>
            <% 
                    }
                } 
            %>
        </tbody>
    </table>
    
    <p style="text-align: center;">
        <a href="registroProducto.xhtml">Agregar más productos</a> | 
        <a href="registrarPedido.jsp">Ir a tomar Pedidos</a>
    </p>
</body>
</html>