package com.biblioteca.modelo;

import com.biblioteca.util.ContraseniaUtilidad;

/**
 * Representa a un usuario del sistema de biblioteca.
 *
 * Extiende {@link Persona} con las credenciales de acceso (nombre de usuario,
 * contraseña encriptada) y el estado de la cuenta (activo/inactivo). La
 * contraseña se encripta automáticamente con SHA-256 al asignarla mediante el
 * constructor o {@link #setContraseniaPlano(String)}.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class Usuario extends Persona {

    private String usuario;
    private String contrasenia;
    private boolean activo;

    /**
     * Crea un usuario vacío (requerido para mapeo desde BD).
     */
    public Usuario() {
    }

    /**
     * Crea un usuario completo. La contraseña se encripta automáticamente.
     *
     * @param cedula Número de cédula de identidad.
     * @param nombre Nombre de pila.
     * @param apellido Apellido.
     * @param direccion Dirección de residencia.
     * @param telefono Número de teléfono.
     * @param usuario Nombre de usuario para login.
     * @param contrasenia Contraseña en texto plano (se encripta internamente
     * con SHA-256).
     * @param activo {@code true} si la cuenta está habilitada.
     */
    public Usuario(String cedula, String nombre, String apellido,
            String direccion, String telefono,
            String usuario, String contrasenia, boolean activo) {
        super(cedula, nombre, apellido, telefono, direccion);
        this.usuario = usuario;
        this.contrasenia = ContraseniaUtilidad.encriptar(contrasenia);
        this.activo = activo;
    }

    /**
     * @return Nombre de usuario para inicio de sesión.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario Nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return Contraseña almacenada en formato hash SHA-256.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña cifrándola antes de guardarla.
     *
     * @param contrasenia Nueva contraseña en texto plano.
     */
    public void setContraseniaPlano(String contrasenia) {
        this.contrasenia = ContraseniaUtilidad.encriptar(contrasenia);
    }

    /**
     * Establece la contraseña directamente en formato hash (usado al leer de la
     * BD).
     *
     * @param contraseniaEncriptada Hash SHA-256 de la contraseña.
     */
    public void setContraseniaEncriptada(String contraseniaEncriptada) {
        this.contrasenia = contraseniaEncriptada;
    }

    /**
     * @return {@code true} si la cuenta está activa.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo Estado de la cuenta.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
