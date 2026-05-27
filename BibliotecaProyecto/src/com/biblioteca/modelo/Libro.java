package com.biblioteca.modelo;

/**
 * Representa un libro del catálogo de la biblioteca.
 *
 * Contiene la información del título, su autor, la clasificación por categoría
 * y editorial, la cantidad de ejemplares disponibles y su estado.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class Libro {

    private long isbn;
    private String nombre;
    private Autor autor;
    private Categoria categoria;
    private Editorial editorial;
    private int cantidad;
    private boolean activo;

    /**
     * Crea un libro vacío (requerido para mapeo desde BD).
     */
    public Libro() {
    }

    /**
     * Crea un libro con todos sus atributos.
     *
     * @param isbn Código ISBN del libro (7–13 dígitos).
     * @param nombre Título del libro.
     * @param autor Autor del libro.
     * @param categoria Categoría literaria del libro.
     * @param editorial Editorial que publicó el libro.
     * @param cantidad Número de ejemplares disponibles.
     * @param activo {@code true} si el libro está disponible en el catálogo.
     */
    public Libro(long isbn, String nombre, Autor autor, Categoria categoria,
            Editorial editorial, int cantidad, boolean activo) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.editorial = editorial;
        this.cantidad = cantidad;
        this.activo = activo;
    }

    /**
     * @return ISBN del libro.
     */
    public long getIsbn() {
        return isbn;
    }

    /**
     * @param isbn ISBN del libro.
     */
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    /**
     * @return Título del libro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Título del libro.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Autor del libro.
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor Autor del libro.
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return Categoría del libro.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria Categoría del libro.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return Editorial del libro.
     */
    public Editorial getEditorial() {
        return editorial;
    }

    /**
     * @param editorial Editorial del libro.
     */
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    /**
     * @return Número de ejemplares disponibles.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad Número de ejemplares disponibles.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return {@code true} si el libro está activo en el catálogo.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo Estado del libro en el catálogo.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
