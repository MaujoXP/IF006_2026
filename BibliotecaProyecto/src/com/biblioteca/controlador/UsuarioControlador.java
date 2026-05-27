package com.biblioteca.controlador;

import com.biblioteca.modelo.Usuario;
import com.biblioteca.repositorio.UsuarioRepositorio;

/**
 * Controlador para la gestión de usuarios del sistema.
 * 
 * Actúa como intermediario entre la vista y el repositorio,
 * aplicando las validaciones de negocio antes de persistir datos.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class UsuarioControlador {

    private UsuarioRepositorio repositorio;

    /**
     * Crea una nueva instancia del controlador e inicializa el repositorio.
     */
    public UsuarioControlador() {
        this.repositorio = new UsuarioRepositorio();
    }

    /**
     * Registra un nuevo usuario en el sistema aplicando validaciones básicas.
     *
     * @param cedula      Cédula de identidad (9 dígitos para CR, hasta 20 para extranjeros).
     * @param nombre      Nombre de pila del usuario.
     * @param apellido    Apellido del usuario.
     * @param direccion   Dirección del usuario (puede estar vacía).
     * @param telefono    Número de teléfono del usuario.
     * @param usuario     Nombre de usuario para inicio de sesión.
     * @param contrasenia Contraseña en texto plano (se encripta internamente).
     * @param idRol       Rol asignado: 1 = ADMIN, 2 = CLIENTE.
     * @return ID del usuario creado si es exitoso;
     *         {@code -2} si hay campos vacíos o inválidos;
     *         {@code -1} si ocurre un error en la base de datos.
     */
    public int registrarUsuario(String cedula, String nombre, String apellido,
                                String direccion, String telefono,
                                String usuario, String contrasenia, int idRol) {

        // Validar cédula: solo dígitos, mínimo 9, máximo 20
        if (cedula == null || cedula.isBlank()) return -2;
        String cedulaLimpia = cedula.trim().replaceAll("[^0-9]", "");
        if (cedulaLimpia.length() < 9 || cedulaLimpia.length() > 20) return -3;

        // Validar nombre (mínimo 2 caracteres, solo letras y espacios)
        if (nombre == null || nombre.trim().length() < 2) return -4;
        if (!nombre.trim().matches("[\\p{L} ]+")) return -4;

        // Validar apellido (mínimo 2 caracteres)
        if (apellido == null || apellido.trim().length() < 2) return -5;
        if (!apellido.trim().matches("[\\p{L} ]+")) return -5;

        // Validar teléfono (8-15 dígitos, guiones permitidos)
        if (telefono == null || telefono.isBlank()) return -6;
        String telLimpio = telefono.trim().replaceAll("[^0-9]", "");
        if (telLimpio.length() < 8 || telLimpio.length() > 15) return -6;

        // Validar usuario (mínimo 3 caracteres, sin espacios)
        if (usuario == null || usuario.trim().length() < 3) return -7;
        if (usuario.contains(" ")) return -7;

        // Validar contraseña (mínimo 4 caracteres)
        if (contrasenia == null || contrasenia.length() < 4) return -8;

        Usuario u = new Usuario(cedulaLimpia, nombre.trim(), apellido.trim(),
                                direccion, telefono.trim(), usuario.trim(), contrasenia, true);
        return repositorio.agregarUsuario(u, idRol);
    }

    /**
     * Busca un usuario por su nombre de login.
     *
     * @param nombreUsuario Nombre de usuario a buscar.
     * @return El objeto {@link Usuario} encontrado, o {@code null} si no existe.
     */
    public Usuario buscarUsuario(String nombreUsuario) {
        return repositorio.consultarUsuarioPorNombre(nombreUsuario);
    }

    /**
     * Actualiza los datos personales y de acceso de un usuario existente.
     *
     * @param usuario          Objeto con los nuevos datos del usuario.
     * @param usuarioOriginal  Nombre de usuario original (antes del cambio).
     * @return {@code true} si ambas actualizaciones (persona y login) fueron exitosas.
     */
    public boolean actualizarUsuario(Usuario usuario, String usuarioOriginal) {
        boolean okPersona = repositorio.actualizarPersonaPorCedula(usuario);
        boolean okLogin   = repositorio.actualizarLoginPorUsuario(usuario, usuarioOriginal);
        return okPersona && okLogin;
    }

    /**
     * Activa o desactiva un usuario en el sistema.
     *
     * @param nombreUsuario Nombre de usuario a modificar.
     * @param nuevoEstado   {@code true} para activar, {@code false} para desactivar.
     * @return {@code true} si el cambio fue exitoso; {@code false} si el usuario no existe o falla la BD.
     */
    public boolean toggleActivo(String nombreUsuario, boolean nuevoEstado) {
        Usuario u = repositorio.consultarUsuarioPorNombre(nombreUsuario);
        if (u == null) return false;
        u.setActivo(nuevoEstado);
        return repositorio.actualizarLoginPorUsuario(u, nombreUsuario);
    }

    /**
     * Elimina un usuario del sistema usando su número de cédula.
     *
     * @param cedula Cédula de la persona a eliminar.
     * @return {@code true} si se eliminó correctamente; {@code false} en caso contrario.
     */
    public boolean eliminarUsuario(String cedula) {
        return repositorio.eliminarUsuarioPorCedula(cedula);
    }
}