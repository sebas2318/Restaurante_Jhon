package com.restaurante.controlador;

import com.restaurante.dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    String user = request.getParameter("txtUser");
    String pass = request.getParameter("txtPass");
    
    UsuarioDAO dao = new UsuarioDAO();
    String rolObtenido = dao.validarUsuario(user, pass); // Consulta a la DB 

    if (rolObtenido != null) {
        HttpSession session = request.getSession();
        session.setAttribute("rol", rolObtenido);
        session.setAttribute("usuario", user);

        // Redirección por rol
        switch (rolObtenido) {
            case "Administrador":
                response.sendRedirect("InventarioServlet");
                break;
            case "Mesero":
                response.sendRedirect("registrarPedido.jsp");
                break;
            case "Cocinero":
                response.sendRedirect("CocinaServlet");
                break;
            default:
                response.sendRedirect("login.jsp?error=1");
                break;
        }
    } else {
        response.sendRedirect("login.jsp?error=1");
    }
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    String logout = request.getParameter("logout");
    if (logout != null && logout.equals("true")) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Destruye la sesión del usuario
        }
        response.sendRedirect("login.jsp"); // Regresa al inicio
    } else {
        // Si entran por URL sin logout, mandarlos al login por seguridad
        response.sendRedirect("login.jsp");
    }
}
}