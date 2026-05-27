package com.biblioteca.modelo;

import java.time.LocalDate;

/**
 * Representa al autor de un libro en el sistema de biblioteca.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class Autor {

    private String nombreCompleto;
    private String nacionalidad;
    private String seudonimo;
    private LocalDate fechaNacimiento;

    /**
     * Crea un autor vacío
     */
    public Autor() {
    }

    /**
     * Crea un autor con todos sus atributos.
     *
     * @param nombreCompleto Nombre completo del autor.
     * @param nacionalidad País de origen del autor.
     * @param seudonimo Alias literario; puede ser null o vacío.
     * @param fechaNacimiento Fecha de nacimiento del autor.
     */
    public Autor(String nombreCompleto, String nacionalidad,
            String seudonimo, LocalDate fechaNacimiento) {
        this.nombreCompleto = nombreCompleto;
        this.nacionalidad = nacionalidad;
        this.seudonimo = seudonimo;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return Nombre completo del autor.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto Nombre completo del autor.
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return Nacionalidad del autor.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad Nacionalidad del autor.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return Seudónimo literario del autor.
     */
    public String getSeudonimo() {
        return seudonimo;
    }

    /**
     * @param seudonimo Seudónimo literario del autor.
     */
    public void setSeudonimo(String seudonimo) {
        this.seudonimo = seudonimo;
    }

    /**
     * @return Fecha de nacimiento del autor.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento Fecha de nacimiento del autor.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
