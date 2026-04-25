package com.restaurante.controlador;

import com.restaurante.dao.ProductoDAO;
import com.restaurante.modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InventarioServlet")
public class InventarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProductoDAO dao = new ProductoDAO();
        List<Producto> lista = dao.listarProductos();
        
        request.setAttribute("listaProductos", lista);
        
        request.getRequestDispatcher("verInventario.jsp").forward(request, response);
    }
}