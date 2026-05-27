package com.biblioteca.modelo;

/**
 * Enumeración de las editoriales disponibles en el sistema.
 *
 * Cada constante tiene un ID numérico que corresponde al registro en la tabla
 * {@code editorial} de la base de datos, y el nombre comercial.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public enum Editorial {

    PLANETA(1, "Planeta"),
    SANTILLANA(2, "Santillana"),
    ALFAGUARA(3, "Alfaguara"),
    NORMA(4, "Norma"),
    ANAGRAMA(5, "Anagrama");

    private final int id;
    private final String nombre;

    /**
     * Constructor del enum.
     *
     * @param id Identificador numérico en la BD.
     * @param nombre Nombre comercial de la editorial.
     */
    Editorial(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * @return ID numérico de la editorial.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Nombre comercial de la editorial.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene una editorial a partir de su ID numérico.
     *
     * @param id ID de la editorial a buscar.
     * @return La constante {@link Editorial} correspondiente.
     * @throws IllegalArgumentException si el ID no corresponde a ninguna
     * editorial.
     */
    public static Editorial fromId(int id) {
        for (Editorial e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Editorial no válida: " + id);
    }
}
