/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persona;

import generica.IFiltro;
import java.time.LocalDate;

/**
 * Entidad Persona
 * @version 1.0.0
 * @author Mauricio
 */
public class Persona implements IFiltro {
    private int id;
    private String cedula;
    private String nombre;
    private LocalDate fechaNacimiento;

    public Persona() {
        
    }

    public Persona(int id, String cedula, String nombre, LocalDate fechaNacimiento) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Persona(Persona persona) {
        this.id = persona.id;
        this.cedula = persona.cedula;
        this.nombre = persona.nombre;
        this.fechaNacimiento = persona.fechaNacimiento;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        String salida = "";
        salida += "IdPeronsa: " + id;
        salida += "\nCedula: " + cedula;
        salida += "\nNombre: " + nombre;
        salida += "\nFecha Nacimiento: " + fechaNacimiento;
        
        return salida;
    }
}
