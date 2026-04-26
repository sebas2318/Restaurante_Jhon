<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Gasto</title>
    <style>
        .form-container { width: 300px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; }
        input, select { width: 100%; margin-bottom: 10px; padding: 8px; }
        button { width: 100%; background-color: #ff4444; color: white; border: none; padding: 10px; cursor: pointer; }
    </style>
</head>
<body>
    <div class="form-container">
        <h3>Registrar Gasto Diario</h3>
        <form action="GastoServlet" method="POST">
            <label>Descripción:</label>
            <input type="text" name="descripcion" placeholder="Ej: Pago de Luz, Compra Carne" required>
            
            <label>Monto ($):</label>
            <input type="number" step="0.01" name="monto" required>
            
            <button type="submit">Guardar Gasto</button>
        </form>
        <br>
        <a href="cierreCaja.jsp">Volver al Cierre</a>
    </div>
</body>
</html>