package com.restaurante.controlador;

import com.restaurante.dao.PedidoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CocinaServlet")
public class CocinaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a la vista de cocina
        response.sendRedirect("pedidosCocina.jsp");
    }

    // Se ejecuta cuando el cocinero presiona el botón "Marcar como Listo"
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        PedidoDAO dao = new PedidoDAO();
        
        // RF02: Actualizamos el estado del pedido a 'Listo' 
        if(dao.actualizarEstadoPedido(idPedido, "Listo")) {
            response.sendRedirect("CocinaServlet"); 
        }
    }
}