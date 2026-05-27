
package com.biblioteca.entidad;


public enum Editorial {

    PLANETA(1, "Planeta"),
    SANTILLANA(2, "Santillana"),
    ALFAGUARA(3, "Alfaguara"),
    NORMA(4, "Norma"),
    ANAGRAMA(5, "Anagrama");

    private final int id;
    private final String nombre;

    Editorial(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static Editorial fromId(int id) {
        for (Editorial e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Editorial no válida: " + id);
    }
}
