package com.biblioteca.repositorio;

import com.biblioteca.modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio para operaciones de persistencia de préstamos y devoluciones.
 * 
 * Gestiona la tabla {@code prestamo} e interactúa con {@link LibroRepositorio}
 * y {@link UsuarioRepositorio} para validar y actualizar datos relacionados.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class PrestamoRepositorio {

    ConexionDB cnx;
    LibroRepositorio libroRepo;
    UsuarioRepositorio usuarioRepo;

    /**
     * Crea una nueva instancia del repositorio.
     */
    public PrestamoRepositorio() {
        cnx = new ConexionDB();
        libroRepo = new LibroRepositorio();
        usuarioRepo = new UsuarioRepositorio();
    }

    /**
     * Registra un nuevo préstamo y decrementa la cantidad disponible del libro.
     *
     * @param prestamo Objeto {@link PrestamoLibro} con los datos del préstamo.
     * @return ID generado del préstamo; {@code -1} si falla la validación o la
     * BD.
     */
    public int registrarPrestamo(PrestamoLibro prestamo) {
        Libro libro = prestamo.getLibro();
        Usuario usuario = prestamo.getUsuario();

        if (libro == null || libro.getCantidad() <= 0) {
            System.out.println("No hay disponibilidad del libro.");
            return -1;
        }

        Integer idUsuario = usuarioRepo.consultarIdUsuarioPorNombre(usuario.getUsuario());
        Integer idLibro = libroRepo.consultarIdLibroPorIsbn(libro.getIsbn());

        if (idUsuario == null || idLibro == null) {
            System.out.println("Usuario o libro no encontrados.");
            return -1;
        }

        try (Connection cn = cnx.obtenerConexion()) {
            String sql = "INSERT INTO prestamo(id_usuario, id_libro, fecha_inicio, fecha_fin, costo_total, multa, activo) VALUES (?,?,?,?,?,?,?)";
            int idPrestamo = -1;
            try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, idUsuario);
                ps.setLong(2, idLibro);
                ps.setDate(3, java.sql.Date.valueOf(prestamo.getFechaInicio()));
                ps.setDate(4, java.sql.Date.valueOf(prestamo.getFechaFin()));
                ps.setInt(5, prestamo.getCostoTotal());
                ps.setInt(6, prestamo.getMulta());
                ps.setBoolean(7, prestamo.isActivo());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idPrestamo = rs.getInt(1);
                    }
                }
            }
            libroRepo.actualizarCantidad(libro.getIsbn(), libro.getCantidad() - 1);
            return idPrestamo;
        } catch (Exception e) {
            System.out.println("Error al registrar préstamo: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Registra la devolución de un libro: actualiza la multa, cierra el
     * préstamo e incrementa la cantidad disponible del libro.
     *
     * @param prestamo Objeto {@link PrestamoLibro} activo a cerrar.
     * @param fechaDevolucion Fecha real de devolución del libro.
     * @return {@code true} si la devolución se registró correctamente;
     * {@code false} si hay error.
     */
    public boolean registrarDevolucion(PrestamoLibro prestamo, LocalDate fechaDevolucion) {
        Libro libro = prestamo.getLibro();
        Usuario usuario = prestamo.getUsuario();

        try (Connection cn = cnx.obtenerConexion()) {
            prestamo.calcularMultaPorAtraso(fechaDevolucion);
            String sqlUpdate = "UPDATE prestamo SET multa=?, activo=0 WHERE id_usuario=? AND id_libro=? AND activo=1";
            try (PreparedStatement ps = cn.prepareStatement(sqlUpdate)) {
                ps.setInt(1, prestamo.getMulta());
                ps.setInt(2, usuarioRepo.consultarIdUsuarioPorNombre(usuario.getUsuario()));
                ps.setLong(3, libroRepo.consultarIdLibroPorIsbn(libro.getIsbn()));
                ps.executeUpdate();
            }
            libroRepo.actualizarCantidad(libro.getIsbn(), libro.getCantidad() + 1);
            prestamo.setActivo(false);
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar devolución: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retorna todos los préstamos con estado activo (libros no devueltos).
     *
     * @return Lista de préstamos activos.
     */
    public List<PrestamoLibro> listarPrestamosActivos() {
        return listarPorFiltro("WHERE p.activo = 1");
    }

    /**
     * Retorna todos los préstamos con estado inactivo (libros ya devueltos).
     *
     * @return Lista de préstamos inactivos.
     */
    public List<PrestamoLibro> listarPrestamosInactivos() {
        return listarPorFiltro("WHERE p.activo = 0");
    }

    /**
     * Retorna el historial completo de préstamos sin importar su estado.
     *
     * @return Lista de todos los préstamos registrados.
     */
    public List<PrestamoLibro> listarTodosPrestamos() {
        return listarPorFiltro("");
    }

    /**
     * Retorna el historial de préstamos de un usuario específico.
     *
     * @param usuario Usuario cuyos préstamos se desean listar.
     * @return Lista de préstamos del usuario.
     */
    public List<PrestamoLibro> listarPrestamosPorUsuario(Usuario usuario) {
        List<PrestamoLibro> prestamos = new ArrayList<>();
        String sql = buildSql("WHERE u.usuario = ?");
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prestamos.add(mapearPrestamo(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar préstamos por usuario: " + e.getMessage());
        }
        return prestamos;
    }

    /**
     * Ejecuta una consulta de préstamos aplicando la cláusula WHERE indicada.
     *
     * @param whereClause Cláusula WHERE completa, o cadena vacía para sin
     * filtro.
     * @return Lista de préstamos resultante.
     */
    private List<PrestamoLibro> listarPorFiltro(String whereClause) {
        List<PrestamoLibro> prestamos = new ArrayList<>();
        String sql = buildSql(whereClause);
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                prestamos.add(mapearPrestamo(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al listar préstamos: " + e.getMessage());
        }
        return prestamos;
    }

    /**
     * Construye el SQL base para consultas de préstamos con una cláusula WHERE
     * opcional.
     *
     * @param whereClause Cláusula WHERE a agregar al final del SQL.
     * @return Cadena SQL completa.
     */
    private String buildSql(String whereClause) {
        return "SELECT p.*, u.usuario, u.contrasenia, u.activo AS usuario_activo, "
                + "per.cedula, per.nombre, per.apellido, per.direccion, per.telefono, "
                + "l.isbn, l.nombre AS nombre_libro, l.cantidad "
                + "FROM prestamo p "
                + "INNER JOIN usuario u ON p.id_usuario = u.id_usuario "
                + "INNER JOIN persona per ON u.id_persona = per.id_persona "
                + "INNER JOIN libro l ON p.id_libro = l.id_libro "
                + whereClause;
    }

    /**
     * Construye un objeto {@link PrestamoLibro} a partir de un
     * {@link ResultSet}.
     *
     * @param rs ResultSet posicionado en la fila a mapear.
     * @return Objeto {@link PrestamoLibro} con usuario y libro asociados.
     * @throws SQLException si ocurre un error al leer el ResultSet.
     */
    private PrestamoLibro mapearPrestamo(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setContraseniaEncriptada(rs.getString("contrasenia"));
        usuario.setActivo(rs.getBoolean("usuario_activo"));
        usuario.setCedula(rs.getString("cedula"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setDireccion(rs.getString("direccion"));
        usuario.setTelefono(rs.getString("telefono"));

        Libro libro = new Libro();
        libro.setIsbn(rs.getLong("isbn"));
        libro.setNombre(rs.getString("nombre_libro"));
        libro.setCantidad(rs.getInt("cantidad"));

        PrestamoLibro prestamo = new PrestamoLibro(
                usuario, libro,
                rs.getDate("fecha_inicio").toLocalDate(),
                rs.getDate("fecha_fin").toLocalDate());
        prestamo.setActivo(rs.getBoolean("activo"));
        prestamo.calcularMultaPorAtraso(LocalDate.now());
        return prestamo;
    }
}
