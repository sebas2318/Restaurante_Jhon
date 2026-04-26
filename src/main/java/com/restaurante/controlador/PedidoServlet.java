package com.restaurante.controlador;

import com.restaurante.dao.PedidoDAO;
import com.restaurante.dao.ProductoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Identificar qué acción quiere realizar el usuario
        String accion = request.getParameter("accion");
        if (accion == null) accion = "registrar"; 

        ProductoDAO productoDao = new ProductoDAO();
        PedidoDAO pedidoDao = new PedidoDAO();

        try {
            if (accion.equals("registrar")) {

                int idProducto = Integer.parseInt(request.getParameter("id_producto"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                String mesa = request.getParameter("mesa");

                boolean exito = productoDao.descontarStock(idProducto, cantidad, mesa);

                if (exito) {
                    response.sendRedirect("confirmacion.jsp");
                } else {
                    request.setAttribute("error", "No hay suficiente stock para completar el pedido");
                    request.getRequestDispatcher("errorPedido.jsp").forward(request, response);
                }

            } else if (accion.equals("cambiarEstado")) {
                
                int idPedido = Integer.parseInt(request.getParameter("idPedido"));
                String nuevoEstado = request.getParameter("nuevoEstado");


                if (pedidoDao.actualizarEstadoPedido(idPedido, nuevoEstado)) {
                    
                    response.sendRedirect("registrarPedido.jsp?msg=exito");
                } else {
                    response.sendRedirect("errorPedido.jsp?err=no_se_pudo_actualizar");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errorPedido.jsp?err=" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registrarPedido.jsp");
    }
}