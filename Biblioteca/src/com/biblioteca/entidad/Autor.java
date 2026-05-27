package com.biblioteca.entidad;

import java.time.LocalDate;

public class Autor extends Persona {

    private String nacionalidad;
    private String seudonimo;

    public Autor() {
    }

    public Autor(String nacionalidad, String seudonimo, int idPersona, String nombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento) {
        super(idPersona, nombre, primerApellido, segundoApellido, fechaNacimiento);
        this.nacionalidad = nacionalidad;
        this.seudonimo = seudonimo;
    }

    public Autor(String nacionalidad, String seudonimo, Persona persona) {

        super(persona.getIdPersona(),
                persona.getNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getFechaNacimiento());

       this.nacionalidad = nacionalidad;
        this.seudonimo = seudonimo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getSeudonimo() {
        return seudonimo;
    }

    public void setSeudonimo(String seudonimo) {
        this.seudonimo = seudonimo;
    }
    
    
}