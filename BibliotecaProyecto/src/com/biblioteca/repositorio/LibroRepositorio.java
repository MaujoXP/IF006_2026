package com.biblioteca.repositorio;

import com.biblioteca.modelo.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Repositorio para operaciones CRUD de la entidad {@link Libro}.
 * 
 * Gestiona la persistencia de libros en la tabla {@code libro}, incluyendo la
 * relación con {@link com.biblioteca.modelo.Autor}.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class LibroRepositorio {

    ConexionDB cnx;
    AutorRepositorio autorRepo;

    /**
     * Crea una nueva instancia del repositorio.
     */
    public LibroRepositorio() {
        cnx = new ConexionDB();
        autorRepo = new AutorRepositorio();
    }

    /**
     * Registra un libro en la base de datos. Si el autor no existe, lo crea
     * primero.
     *
     * @param libro Objeto {@link Libro} con todos sus datos.
     * @return ID generado del libro; {@code -1} si ocurre un error.
     */
    public int registrarLibro(Libro libro) {
        int idAutor = autorRepo.registrarAutor(libro.getAutor());
        Connection cn = null;
        try {
            cn = cnx.obtenerConexion();
            String sql = "INSERT INTO libro(isbn, nombre, id_categoria, id_editorial, id_autor, cantidad, activo) VALUES (?,?,?,?,?,?,?)";
            int idLibro = -1;
            try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, libro.getIsbn());
                ps.setString(2, libro.getNombre());
                ps.setInt(3, libro.getCategoria().getId());
                ps.setInt(4, libro.getEditorial().getId());
                ps.setInt(5, idAutor);
                ps.setInt(6, libro.getCantidad());
                ps.setBoolean(7, libro.isActivo());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idLibro = rs.getInt(1);
                    }
                }
            }
            return idLibro;
        } catch (Exception e) {
            System.out.println("Error al registrar libro: " + e.getMessage());
            return -1;
        } finally {
            ConexionDB.cerrarConexion(cn);
        }
    }

    /**
     * Busca un libro por su ISBN incluyendo los datos del autor.
     *
     * @param isbn ISBN del libro a buscar.
     * @return Objeto {@link Libro} con su autor; {@code null} si no existe.
     */
    public Libro consultarLibroPorIsbn(long isbn) {
        String sql = "SELECT l.*, a.nombre_completo, a.nacionalidad, a.seudonimo, a.fecha_nacimiento "
                + "FROM libro l INNER JOIN autor a ON l.id_autor = a.id_autor WHERE l.isbn = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearLibro(rs);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar libro: " + e.getMessage());
        }
        return null;
    }

    /**
     * Obtiene el ID interno de un libro a partir de su ISBN.
     *
     * @param isbn ISBN del libro.
     * @return ID del libro; {@code null} si no se encuentra.
     */
    public Integer consultarIdLibroPorIsbn(long isbn) {
        String sql = "SELECT id_libro FROM libro WHERE isbn = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_libro");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar id_libro: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna la lista de todos los ISBNs registrados en el sistema.
     *
     * @return Lista de ISBNs; vacía si no hay libros.
     */
    public ArrayList<Long> obtenerTodosLosIsbn() {
        ArrayList<Long> isbns = new ArrayList<>();
        String sql = "SELECT isbn FROM libro ORDER BY id_libro";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                isbns.add(rs.getLong("isbn"));
            }
        } catch (Exception e) {
            System.out.println("Error al consultar ISBNs: " + e.getMessage());
        }
        return isbns;
    }

    /**
     * Actualiza la cantidad de ejemplares disponibles de un libro.
     *
     * @param isbn ISBN del libro a actualizar.
     * @param nuevaCantidad Nueva cantidad de ejemplares.
     * @return {@code true} si se actualizó; {@code false} en caso contrario.
     */
    public boolean actualizarCantidad(long isbn, int nuevaCantidad) {
        String sql = "UPDATE libro SET cantidad = ? WHERE isbn = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, nuevaCantidad);
            ps.setLong(2, isbn);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar cantidad: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un libro del sistema por su ISBN.
     *
     * @param isbn ISBN del libro a eliminar.
     * @return {@code true} si se eliminó; {@code false} si no se encontró o hay
     * préstamos activos.
     */
    public boolean eliminarLibroPorIsbn(long isbn) {
        String sql = "DELETE FROM libro WHERE isbn = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, isbn);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retorna la lista completa de libros con sus autores.
     *
     * @return Lista de objetos {@link Libro}; vacía si no hay registros.
     */
    public ArrayList<Libro> consultarTodosLosLibros() {
        ArrayList<Libro> libros = new ArrayList<>();
        String sql = "SELECT l.*, a.nombre_completo, a.nacionalidad, a.seudonimo, a.fecha_nacimiento "
                + "FROM libro l INNER JOIN autor a ON l.id_autor = a.id_autor ORDER BY l.id_libro";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                libros.add(mapearLibro(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al consultar todos los libros: " + e.getMessage());
        }
        return libros;
    }

    /**
     * Construye un objeto {@link Libro} a partir de un {@link ResultSet}.
     *
     * @param rs ResultSet posicionado en la fila a mapear.
     * @return Objeto {@link Libro} con sus datos y autor.
     * @throws SQLException si ocurre un error al leer el ResultSet.
     */
    private Libro mapearLibro(ResultSet rs) throws SQLException {
        Libro libro = new Libro();
        libro.setIsbn(rs.getLong("isbn"));
        libro.setNombre(rs.getString("nombre"));
        libro.setCantidad(rs.getInt("cantidad"));
        libro.setActivo(rs.getBoolean("activo"));
        libro.setCategoria(Categoria.fromId(rs.getInt("id_categoria")));
        libro.setEditorial(Editorial.fromId(rs.getInt("id_editorial")));
        Autor autor = new Autor();
        autor.setNombreCompleto(rs.getString("nombre_completo"));
        autor.setNacionalidad(rs.getString("nacionalidad"));
        autor.setSeudonimo(rs.getString("seudonimo"));
        autor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        libro.setAutor(autor);
        return libro;
    }
}
