package com.biblioteca.repositorio;

import com.biblioteca.modelo.Autor;
import java.sql.*;
import java.time.LocalDate;

/**
 * Repositorio para operaciones de persistencia de la entidad {@link Autor}.
 *
 * Maneja la inserción y consulta de autores en la tabla {@code autor}. Si un
 * autor ya existe (mismo nombre, nacionalidad y fecha de nacimiento), se
 * reutiliza su ID en lugar de insertar un duplicado.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class AutorRepositorio {

    ConexionDB cnx;

    /**
     * Crea una nueva instancia del repositorio.
     */
    public AutorRepositorio() {
        cnx = new ConexionDB();
    }

    /**
     * Registra un autor en la base de datos. Si ya existe uno con los mismos
     * datos, retorna el ID existente sin insertar un duplicado.
     *
     * @param autor Objeto {@link Autor} con los datos a registrar.
     * @return ID del autor (nuevo o existente); {@code -1} si ocurre un error.
     */
    public int registrarAutor(Autor autor) {
        Integer idExistente = consultarIdAutor(
                autor.getNombreCompleto(), autor.getNacionalidad(), autor.getFechaNacimiento());
        if (idExistente != null) {
            System.out.println("Autor ya existe, no se inserta.");
            return idExistente;
        }

        Connection cn = null;
        try {
            cn = cnx.obtenerConexion();
            String sql = "INSERT INTO autor(nombre_completo, nacionalidad, seudonimo, fecha_nacimiento) VALUES (?,?,?,?)";
            int idAutor = -1;
            try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, autor.getNombreCompleto());
                ps.setString(2, autor.getNacionalidad());
                ps.setString(3, autor.getSeudonimo());
                ps.setDate(4, java.sql.Date.valueOf(autor.getFechaNacimiento()));
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idAutor = rs.getInt(1);
                    }
                }
            }
            return idAutor;
        } catch (Exception e) {
            System.out.println("Error al registrar autor: " + e.getMessage());
            return -1;
        } finally {
            ConexionDB.cerrarConexion(cn);
        }
    }

    /**
     * Busca el ID de un autor por sus datos identificativos.
     *
     * @param nombreCompleto Nombre completo del autor.
     * @param nacionalidad Nacionalidad del autor.
     * @param fechaNacimiento Fecha de nacimiento del autor.
     * @return ID del autor si existe, o {@code null} si no se encuentra.
     */
    public Integer consultarIdAutor(String nombreCompleto, String nacionalidad, LocalDate fechaNacimiento) {
        String sql = "SELECT id_autor FROM autor WHERE nombre_completo = ? AND nacionalidad = ? AND fecha_nacimiento = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombreCompleto);
            ps.setString(2, nacionalidad);
            ps.setDate(3, java.sql.Date.valueOf(fechaNacimiento));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_autor");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar autor: " + e.getMessage());
        }
        return null;
    }
}
