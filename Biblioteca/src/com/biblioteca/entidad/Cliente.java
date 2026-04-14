/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.entidad;

import java.time.LocalDate;

/**
 *
 * @author Gaby
 */
public class Cliente extends Usuario {
    private int idCliente;
    private String cedula;
    private String carnet;
    private String telefono;
    private String correo;
    private int estado;

    public Cliente(int idCliente, String cedula, String carnet, String telefono, String correo, int estado, int idUsuario, String nombreLogin, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String contrasenna, String perfil, boolean activo) {
        super(idUsuario, nombreLogin, primerApellido, segundoApellido, fechaNacimiento, contrasenna, perfil, activo);
        this.idCliente = idCliente;
        this.cedula = cedula;
        this.carnet = carnet;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getCedula() {
        return cedula;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public int isEstado() {
        return estado;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", cedula=" + cedula + ", carnet=" + carnet + ", telefono=" + telefono + ", correo=" + correo + ", estado=" + estado + '}';
    }
}
