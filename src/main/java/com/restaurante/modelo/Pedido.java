package com.restaurante.modelo;

public class Pedido {
    private int id;
    private int idProducto;
    private String mesa;
    private String nombreProducto;
    private int cantidad;
    private String estado;
    private double total;

    public Pedido() {}

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public String getMesa() { 
        return mesa; 
    }
    public void setMesa(String mesa) { 
        this.mesa = mesa; 
    }

    public String getNombreProducto() { 
        return nombreProducto; 
    }
    public void setNombreProducto(String nombreProducto) { 
        this.nombreProducto = nombreProducto; 
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }

    public String getEstado() { 
        return estado; 
    }
    public void setEstado(String estado) { 
        this.estado = estado; 
    }
    
    public double getTotal() { 
        return total; 
    }
    public void setTotal(double total) { 
        this.total = total;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}