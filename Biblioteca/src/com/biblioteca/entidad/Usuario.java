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
public class Usuario {
    private int idUsuario;
    private String nombreLogin;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String contrasenna;
    private String perfil;
    private boolean activo;

    public Usuario(int idUsuario, String nombreLogin, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String contrasenna, String perfil, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombreLogin = nombreLogin;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasenna = contrasenna;
        this.perfil = perfil;
        this.activo = activo;
    }

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreLogin() {
        return nombreLogin;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public String getPerfil() {
        return perfil;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombreLogin=" + nombreLogin + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", fechaNacimiento=" + fechaNacimiento + ", contrasenna=" + contrasenna + ", perfil=" + perfil + ", activo=" + activo + '}';
    }
    
    
}
