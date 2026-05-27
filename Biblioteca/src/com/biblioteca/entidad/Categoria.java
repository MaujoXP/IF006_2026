package com.biblioteca.entidad;

public enum Categoria {

    NOVELA(1, "Novela"),
    CIENCIA(2, "Ciencia"),
    HISTORIA(3, "Historia"),
    TECNOLOGIA(4, "Tecnología"),
    INFANTIL(5, "Infantil"),
    FANTASIA(6, "Fantasía");

    private final int id;
    private final String descripcion;

    Categoria(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Categoria fromId(int id) {
        for (Categoria c : values()) {
            if (c.id == id) {
                return c;
            }
        }
        throw new IllegalArgumentException("Categoría no válida: " + id);
    }
}