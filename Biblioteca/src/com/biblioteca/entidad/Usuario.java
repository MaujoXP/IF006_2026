package com.biblioteca.entidad;

import java.time.LocalDate;

public class Usuario {
    
    private int idUsuario;
    private String nombreLogin;
    private String contrasenna;
    private String perfil;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreLogin, String contrasenna, String perfil, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombreLogin = nombreLogin;
        this.contrasenna = contrasenna;
        this.perfil = perfil;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreLogin() {
        return nombreLogin;
    }

    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}