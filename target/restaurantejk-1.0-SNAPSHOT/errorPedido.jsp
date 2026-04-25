<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><title>Error</title></head>
    <body>
        <h2 style="color: red;">❌ Error en el Pedido</h2>
        <p><%= request.getAttribute("error") %></p>
        <a href="registrarPedido.jsp">Volver a intentar</a>
    </body>
</html>