/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.entidad;

/**
 *
 * @author Gaby
 */
public class Inventario {
    private int idInventario;
    private int idLibro;
    private byte cantidad;

    public Inventario(int idInventario, int idLibro, byte cantidad) {
        this.idInventario = idInventario;
        this.idLibro = idLibro;
        this.cantidad = cantidad;
    }

    public Inventario() {
    }

    public int getIdInventario() {
        return idInventario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public byte getCantidad() {
        return cantidad;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public void setCantidad(byte cantidad) {
        this.cantidad = cantidad;
    }
    
    public String toString() {
        String salida = "idInventario: " + idInventario
                + "\nidLibro" + idLibro
                + "\nCantidad" + cantidad;
        return salida;
     }
}
