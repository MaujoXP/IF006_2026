package com.biblioteca.controlador;

import com.biblioteca.modelo.Usuario;
import com.biblioteca.repositorio.UsuarioRepositorio;

/**
 * Controlador de autenticación del sistema.
 * 
 * Se encarga de validar las credenciales del usuario contra
 * la base de datos y verificar que posea el rol requerido.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class LoginControlador {

    private UsuarioRepositorio repositorio;

    /**
     * Crea una nueva instancia del controlador e inicializa el repositorio.
     */
    public LoginControlador() {
        this.repositorio = new UsuarioRepositorio();
    }

    /**
     * Intenta autenticar a un usuario con las credenciales proporcionadas.
     * 
     * La contraseña se encripta con SHA-256 antes de compararse con la almacenada.
     * Además se verifica que el usuario tenga el rol indicado.
     *
     * @param usuario    Nombre de usuario ingresado.
     * @param contrasenia Contraseña en texto plano ingresada.
     * @param idRol      ID del rol requerido para el acceso (1 = ADMIN, 2 = CLIENTE).
     * @return El objeto {@link Usuario} autenticado si las credenciales son correctas y tiene el rol;
     *         {@code null} si el usuario no existe, la contraseña es incorrecta o no tiene el rol.
     */
    public Usuario login(String usuario, String contrasenia, int idRol) {
        return repositorio.iniciarSesion(usuario, contrasenia, idRol);
    }
}