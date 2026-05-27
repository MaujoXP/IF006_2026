package com.biblioteca.modelo;

/**
 * Enumeración de las categorías disponibles para clasificar libros.
 *
 * Cada constante tiene un ID numérico que corresponde al registro en la tabla
 * {@code categoria} de la base de datos, y una descripción legible.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public enum Categoria {

    NOVELA(1, "Novela"),
    CIENCIA(2, "Ciencia"),
    HISTORIA(3, "Historia"),
    TECNOLOGIA(4, "Tecnología"),
    INFANTIL(5, "Infantil"),
    FANTASIA(6, "Fantasía");

    private final int id;
    private final String descripcion;

    /**
     * Constructor del enum.
     *
     * @param id Identificador numérico en la BD.
     * @param descripcion Nombre legible de la categoría.
     */
    Categoria(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * @return ID numérico de la categoría.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Descripción legible de la categoría.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene una categoría a partir de su ID numérico.
     *
     * @param id ID de la categoría a buscar.
     * @return La constante {@link Categoria} correspondiente.
     * @throws IllegalArgumentException si el ID no corresponde a ninguna
     * categoría.
     */
    public static Categoria fromId(int id) {
        for (Categoria c : values()) {
            if (c.id == id) {
                return c;
            }
        }
        throw new IllegalArgumentException("Categoría no válida: " + id);
    }
}
