package com.biblioteca.entidad;

import java.time.LocalDate;

public class Cliente extends Persona {

    private String carnet;
    private String cedula;
    private String telefono;
    private String correo;
    private int estado;

    public Cliente() {
    }

    public Cliente(String carnet, String cedula, String telefono, String correo,
            int estado, int idPersona, String nombre, String primerApellido,
            String segundoApellido, LocalDate fechaNacimiento) {
        super(idPersona, nombre, primerApellido, segundoApellido, fechaNacimiento);
        this.carnet = carnet;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public Cliente(String carnet, String cedula, String telefono, String correo,
            int estado, Persona persona) {

        super(persona.getIdPersona(),
                persona.getNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getFechaNacimiento());

        this.carnet = carnet;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    

}
