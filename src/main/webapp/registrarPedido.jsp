<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Restaurante Jhon - Nuevo Pedido</title>
        <style>
            body { font-family: sans-serif; margin: 20px; }
            .container { width: 300px; border: 1px solid #ccc; padding: 20px; border-radius: 8px; }
            input { width: 100%; margin-bottom: 10px; padding: 5px; }
            button { width: 100%; background: #28a745; color: white; border: none; padding: 10px; cursor: pointer; }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Tomar Pedido</h2>
            <form action="PedidoServlet" method="POST">
                Mesa: 
                <input type="text" name="mesa" required placeholder="Ej: Mesa 1">
                ID Producto: 
                <input type="number" name="idProducto" required>
                Cantidad: 
                <input type="number" name="cantidad" required min="1">
                
                <button type="submit">Enviar a Cocina</button>
            </form>
        </div>
        <a href="registroProducto.xhtml">Ir a Módulo agregar producto</a>
        <a href="InventarioServlet">Ver inventario</a>
    </body>
</html>