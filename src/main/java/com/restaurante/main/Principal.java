package com.restaurante.main;

import com.restaurante.modelo.Producto;
import com.restaurante.dao.ProductoDAO;

public class Principal {
    public static void main(String[] args) {
        ProductoDAO control = new ProductoDAO();

        Producto nuevo = new Producto("Perro Especial", 10000, 20);

        System.out.println("Intentando registrar producto...");
        if (control.registrar(nuevo)) {
            System.out.println("✅ ¡Producto guardado con éxito!");
        }

        control.listarProductos();
    }
}