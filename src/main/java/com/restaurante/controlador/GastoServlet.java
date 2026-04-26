package com.restaurante.controlador;

import com.restaurante.dao.PedidoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GastoServlet")
public class GastoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String desc = request.getParameter("descripcion");
        double monto = Double.parseDouble(request.getParameter("monto"));
        
        PedidoDAO dao = new PedidoDAO();
        if(dao.registrarGasto(desc, monto)) {
            response.sendRedirect("cierreCaja.jsp?msg=GastoRegistrado");
        } else {
            response.sendRedirect("registrarGasto.jsp?err=Error");
        }
    }
}