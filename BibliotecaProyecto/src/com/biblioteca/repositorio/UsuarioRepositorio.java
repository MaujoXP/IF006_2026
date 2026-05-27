package com.biblioteca.repositorio;

import com.biblioteca.modelo.Usuario;
import com.biblioteca.util.ContraseniaUtilidad;
import java.sql.*;

/**
 * Repositorio para operaciones CRUD de la entidad {@link Usuario}.
 * 
 * Gestiona las tablas {@code persona}, {@code usuario} y {@code usuario_rol},
 * usando transacciones para garantizar la integridad al registrar un usuario
 * nuevo.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class UsuarioRepositorio {

    ConexionDB cnx;

    /**
     * Crea una nueva instancia del repositorio.
     */
    public UsuarioRepositorio() {
        cnx = new ConexionDB();
    }

    /**
     * Registra un nuevo usuario en el sistema dentro de una transacción.
     * 
     * Si la persona ya existe por cédula, se reutiliza su registro. No permite
     * crear un usuario con el mismo rol si ya lo tiene asignado.
     *
     * @param usuario Datos del usuario a registrar. La contraseña debe venir ya
     * encriptada (se encripta en el constructor de {@link Usuario}).
     * @param idRol Rol a asignar: 1 = ADMIN, 2 = CLIENTE.
     * @return ID generado del usuario; {@code -1} si falla algún paso de la
     * transacción.
     */
    public int agregarUsuario(Usuario usuario, int idRol) {
        Connection cn = null;
        try {
            cn = cnx.obtenerConexion();
            cn.setAutoCommit(false);

            Integer idPersona = consultarIdPersonaPorCedula(usuario.getCedula(), cn);
            if (idPersona == null) {
                idPersona = insertarPersona(cn, usuario);
            }
            if (idPersona == null || idPersona == -1) {
                cn.rollback();
                return -1;
            }

            if (personaYaTieneRol(idPersona, idRol, cn)) {
                System.out.println("La persona ya tiene este rol.");
                cn.rollback();
                return -1;
            }

            int idUsuario = insertarUsuario(cn, usuario, idPersona);
            if (idUsuario == -1) {
                cn.rollback();
                return -1;
            }

            if (!agregarRolAUsuario(cn, idUsuario, idRol)) {
                cn.rollback();
                return -1;
            }

            cn.commit();
            return idUsuario;
        } catch (Exception e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return -1;
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                }
                ConexionDB.cerrarConexion(cn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Busca el ID de una persona por su cédula dentro de una transacción
     * activa.
     *
     * @param cedula Cédula a buscar.
     * @param cn Conexión de la transacción en curso.
     * @return ID de la persona; {@code null} si no existe.
     * @throws SQLException si falla la consulta.
     */
    private Integer consultarIdPersonaPorCedula(String cedula, Connection cn) throws SQLException {
        String sql = "SELECT id_persona FROM persona WHERE cedula = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cedula.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_persona");
                }
            }
        }
        return null;
    }

    /**
     * Inserta una nueva persona en la BD dentro de una transacción activa.
     *
     * @param cn Conexión de la transacción en curso.
     * @param usuario Objeto con los datos personales a insertar.
     * @return ID generado de la persona; {@code -1} si falla.
     * @throws SQLException si falla la inserción.
     */
    private int insertarPersona(Connection cn, Usuario usuario) throws SQLException {
        String sql = "INSERT INTO persona(cedula, nombre, apellido, direccion, telefono) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getCedula());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    /**
     * Inserta un nuevo registro de usuario (credenciales) dentro de una
     * transacción.
     *
     * @param cn Conexión de la transacción en curso.
     * @param usuario Objeto con las credenciales a insertar.
     * @param idPersona ID de la persona asociada al usuario.
     * @return ID generado del usuario; {@code -1} si falla.
     * @throws SQLException si falla la inserción.
     */
    private int insertarUsuario(Connection cn, Usuario usuario, int idPersona) throws SQLException {
        String sql = "INSERT INTO usuario(id_persona, usuario, contrasenia, activo) VALUES (?,?,?,?)";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idPersona);
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getContrasenia());
            ps.setBoolean(4, usuario.isActivo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    /**
     * Asigna un rol a un usuario dentro de una transacción activa.
     *
     * @param cn Conexión de la transacción en curso.
     * @param idUsuario ID del usuario.
     * @param idRol ID del rol a asignar.
     * @return {@code true} si se insertó el rol correctamente.
     * @throws SQLException si falla la inserción.
     */
    private boolean agregarRolAUsuario(Connection cn, int idUsuario, int idRol) throws SQLException {
        String sql = "INSERT INTO usuario_rol(id_usuario, id_rol) VALUES (?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idRol);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Asigna un rol a un usuario (versión pública con conexión propia).
     *
     * @param idUsuario ID del usuario.
     * @param idRol ID del rol a asignar.
     * @return {@code true} si se asignó correctamente.
     */
    public boolean agregarRolAUsuario(int idUsuario, int idRol) {
        try (Connection cn = cnx.obtenerConexion()) {
            String sql = "INSERT INTO usuario_rol(id_usuario, id_rol) VALUES (?, ?)";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);
                ps.setInt(2, idRol);
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al asignar rol: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina el registro de usuario (no la persona) por cédula.
     *
     * @param cedula Cédula de la persona cuyo usuario se desea eliminar.
     * @return {@code true} si se eliminó; {@code false} en caso contrario.
     */
    public boolean eliminarUsuarioPorCedula(String cedula) {
        String sql = "DELETE u FROM usuario u INNER JOIN persona p ON u.id_persona = p.id_persona WHERE p.cedula = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un usuario completo (con datos personales) por nombre de login.
     *
     * @param nombreUsuario Nombre de usuario a buscar.
     * @return Objeto {@link Usuario} con todos sus datos; {@code null} si no
     * existe.
     */
    public Usuario consultarUsuarioPorNombre(String nombreUsuario) {
        String sql = "SELECT u.id_usuario, u.usuario, u.contrasenia, u.activo, "
                + "p.id_persona, p.cedula, p.nombre, p.apellido, p.direccion, p.telefono "
                + "FROM usuario u INNER JOIN persona p ON u.id_persona = p.id_persona "
                + "WHERE u.usuario = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setContraseniaEncriptada(rs.getString("contrasenia"));
                    usuario.setActivo(rs.getBoolean("activo"));
                    usuario.setCedula(rs.getString("cedula"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setTelefono(rs.getString("telefono"));
                    return usuario;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar usuario: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza los datos personales de una persona identificada por su cédula.
     *
     * @param usuario Objeto con los nuevos datos personales y la cédula como
     * identificador.
     * @return {@code true} si se actualizó al menos una fila.
     */
    public boolean actualizarPersonaPorCedula(Usuario usuario) {
        String sql = "UPDATE persona SET nombre=?, apellido=?, direccion=?, telefono=? WHERE cedula=?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDireccion());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getCedula());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza el nombre de usuario, contraseña y estado de una cuenta.
     *
     * @param usuario Objeto con los nuevos datos de acceso.
     * @param usuarioOriginal Nombre de usuario actual (antes del cambio).
     * @return {@code true} si se actualizó correctamente.
     */
    public boolean actualizarLoginPorUsuario(Usuario usuario, String usuarioOriginal) {
        String sql = "UPDATE usuario SET usuario=?, contrasenia=?, activo=? WHERE usuario=?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasenia());
            ps.setBoolean(3, usuario.isActivo());
            ps.setString(4, usuarioOriginal);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar login: " + e.getMessage());
            return false;
        }
    }

    /**
     * Autentica a un usuario verificando credenciales y rol.
     *
     * @param usuario Nombre de usuario.
     * @param contrasenia Contraseña en texto plano.
     * @param idRol Rol requerido para el acceso.
     * @return Objeto {@link Usuario} autenticado; {@code null} si las
     * credenciales son incorrectas o el usuario no tiene el rol indicado.
     */
    public Usuario iniciarSesion(String usuario, String contrasenia, int idRol) {
        Usuario encontrado = consultarUsuarioPorNombre(usuario);
        if (encontrado == null) {
            return null;
        }
        String hash = ContraseniaUtilidad.encriptar(contrasenia);
        if (!encontrado.getContrasenia().equals(hash)) {
            return null;
        }
        Integer idUsuario = consultarIdUsuarioPorNombre(encontrado.getUsuario());
        return (idUsuario != null && tieneRol(idUsuario, idRol)) ? encontrado : null;
    }

    /**
     * Obtiene el ID interno de un usuario por su nombre de login.
     *
     * @param nombreUsuario Nombre de usuario a buscar.
     * @return ID del usuario; {@code null} si no existe.
     */
    public Integer consultarIdUsuarioPorNombre(String nombreUsuario) {
        String sql = "SELECT id_usuario FROM usuario WHERE usuario = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_usuario");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar id_usuario: " + e.getMessage());
        }
        return null;
    }

    /**
     * Verifica si un usuario tiene asignado un rol específico.
     *
     * @param idUsuario ID del usuario.
     * @param idRol ID del rol a verificar.
     * @return {@code true} si el usuario tiene el rol; {@code false} en caso
     * contrario.
     */
    public boolean tieneRol(int idUsuario, int idRol) {
        String sql = "SELECT 1 FROM usuario_rol WHERE id_usuario = ? AND id_rol = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("Error al verificar rol: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica si una persona ya tiene asignado un rol (para evitar
     * duplicados).
     *
     * @param idPersona ID de la persona.
     * @param idRol ID del rol a verificar.
     * @param cn Conexión de la transacción en curso.
     * @return {@code true} si la persona ya tiene ese rol.
     * @throws SQLException si falla la consulta.
     */
    private boolean personaYaTieneRol(int idPersona, int idRol, Connection cn) throws SQLException {
        String sql = "SELECT 1 FROM usuario u JOIN usuario_rol ur ON u.id_usuario = ur.id_usuario "
                + "WHERE u.id_persona = ? AND ur.id_rol = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ps.setInt(2, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
