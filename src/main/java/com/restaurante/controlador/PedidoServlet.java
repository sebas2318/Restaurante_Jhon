package com.restaurante.controlador;

import com.restaurante.dao.ProductoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// El nombre de la URL para el formulario HTML
@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String mesa = request.getParameter("mesa");

        ProductoDAO productoDao = new ProductoDAO();
        

        boolean exito = productoDao.descontarStock(idProducto, cantidad, mesa);


        if(exito) {
            response.sendRedirect("confirmacion.jsp");
        } else {
            request.setAttribute("error", "No hay suficiente stock para completar el pedido");
            request.getRequestDispatcher("errorPedido.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("registrarPedido.jsp");
    }
}