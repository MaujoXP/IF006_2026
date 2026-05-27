package com.biblioteca.modelo;

/**
 * Clase base que representa a una persona en el sistema.
 * 
 * Es extendida por {@link Usuario} para agregar las credenciales de acceso.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class Persona {

    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String direccion;

    /**
     * Crea una persona vacía (requerido para herencia y mapeo).
     */
    public Persona() {
    }

    /**
     * Crea una persona con todos sus datos personales.
     *
     * @param cedula Número de cédula de identidad.
     * @param nombre Nombre de pila.
     * @param apellido Apellido.
     * @param telefono Número de teléfono.
     * @param direccion Dirección de residencia.
     */
    public Persona(String cedula, String nombre, String apellido,
            String telefono, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    /**
     * @return Número de cédula.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula Número de cédula.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return Nombre de pila.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Nombre de pila.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Apellido.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido Apellido.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return Número de teléfono.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono Número de teléfono.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return Dirección de residencia.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion Dirección de residencia.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
