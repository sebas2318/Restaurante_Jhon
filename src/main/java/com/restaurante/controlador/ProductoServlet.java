package com.restaurante.controlador;

import com.restaurante.dao.ProductoDAO;
import com.restaurante.modelo.Producto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String nombre = request.getParameter("txtNombre");
            double precio = Double.parseDouble(request.getParameter("txtPrecio"));
            int stock = Integer.parseInt(request.getParameter("txtStock"));

            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setStock(stock);

            ProductoDAO dao = new ProductoDAO();
            boolean exito = dao.guardarProducto(nuevoProducto);

            if (exito) {
                response.sendRedirect("registroProducto.xhtml?msj=exito");
            } else {
                response.sendRedirect("registroProducto.xhtml?msj=error");
            }
            
        } catch (NumberFormatException e) {
            response.sendRedirect("registroProducto.xhtml?msj=invalid_data");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registroProducto.xhtml");
    }
}