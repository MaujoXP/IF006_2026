/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import interfaces.IServicio;

/**
 * Define el modelo de un servicio
 * @author Gaby
 */
public abstract class Servicio implements IServicio {
    private int idServicio;
    private String codigo;
    private String nombre;

    public Servicio() {
        
    }
    
    public Servicio(int idServicio, String codigo, String nombre) {
        this.idServicio = idServicio;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Servicio{" + "idServicio=" + idServicio + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }   
}
