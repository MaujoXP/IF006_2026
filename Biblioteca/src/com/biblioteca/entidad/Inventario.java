package com.biblioteca.entidad;

public class Inventario {
    
    private int idInventario;
    private int idLibro;
    private byte cantidad;

    public Inventario() {
    }

    public Inventario(int idInventario, int idLibro, byte cantidad) {
        this.idInventario = idInventario;
        this.idLibro = idLibro;
        this.cantidad = cantidad;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public byte getCantidad() {
        return cantidad;
    }

    public void setCantidad(byte cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "IdInventario: " + idInventario 
                +"\nIdLibro: " + idLibro 
                +"\nCantidad: " + cantidad;
    }
    
    
    
}
