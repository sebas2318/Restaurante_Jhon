<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Acceso - Restaurante JK</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #e9ecef; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .login-card { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 10px 25px rgba(0,0,0,0.1); width: 350px; }
        h2 { text-align: center; color: #343a40; margin-bottom: 25px; }
        input { width: 100%; padding: 12px; margin: 10px 0; border: 1px solid #ced4da; border-radius: 6px; box-sizing: border-box; }
        button { width: 100%; background: #007bff; color: white; border: none; padding: 12px; border-radius: 6px; cursor: pointer; font-size: 16px; transition: 0.3s; }
        button:hover { background: #0056b3; }
        .error-msg { color: #dc3545; text-align: center; font-size: 14px; margin-top: 10px; }
    </style>
</head>
<body>
    <div class="login-card">
        <h2>Restaurante JK</h2>
        <form action="LoginServlet" method="POST">
            <input type="text" name="txtUser" placeholder="Nombre de usuario" required>
            <input type="password" name="txtPass" placeholder="Contraseña" required>
            <button type="submit">Ingresar al Sistema</button>
        </form>
        <% if(request.getParameter("error") != null) { %>
            <p class="error-msg">Usuario o contraseña incorrectos. Intente de nuevo.</p>
        <% } %>
    </div>
</body>
</html>