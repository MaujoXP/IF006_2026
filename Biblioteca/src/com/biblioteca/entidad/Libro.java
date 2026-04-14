/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.entidad;

/**
 *
 * @author Gaby
 */
public class Libro {

    private int isbn;
    private int idLibro;
    private String nombre;
    private short idAutor;
    private byte idCategoria;
    private byte idEditorial;
    private boolean activo;

    public Libro(int isbn, int idLibro, String nombre, short idAutor, byte idCategoria, byte idEditorial, boolean activo) {
        this.isbn = isbn;
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
        this.idEditorial = idEditorial;
        this.activo = activo;
    }

    public Libro() {
    }

    public int getIsbn() {
        return isbn;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getNombre() {
        return nombre;
    }

    public short getIdAutor() {
        return idAutor;
    }

    public byte getIdCategoria() {
        return idCategoria;
    }

    public byte getIdEditorial() {
        return idEditorial;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdAutor(short idAutor) {
        this.idAutor = idAutor;
    }

    public void setIdCategoria(byte idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setIdEditorial(byte idEditorial) {
        this.idEditorial = idEditorial;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String toString() {
        String salida = "ID: " + idLibro
                + "\nNombre: " + nombre
                + "\nISBN: " + isbn
                + "\nidAutor: " + idAutor
                + "\nidCategoria: " + idCategoria
                + "\nidEditorial: " + idEditorial
                + "\nActivo" + (activo ? "Si" : "No");
        return salida;
    }
}
