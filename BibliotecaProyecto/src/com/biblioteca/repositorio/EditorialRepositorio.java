package com.biblioteca.repositorio;

import java.sql.*;
import java.util.ArrayList;

/**
 * Repositorio de solo lectura para la entidad Editorial.
 * 
 * Consulta la tabla {@code editorial} de la base de datos.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class EditorialRepositorio {

    ConexionDB cnx;

    /**
     * Crea una nueva instancia del repositorio.
     */
    public EditorialRepositorio() {
        cnx = new ConexionDB();
    }

    /**
     * Busca el ID de una editorial por su nombre.
     *
     * @param nombreEditorial Nombre de la editorial (se normaliza a
     * mayúsculas).
     * @return ID de la editorial si existe; {@code null} si no se encuentra.
     */
    public Integer consultarIdEditorialPorNombre(String nombreEditorial) {
        String sql = "SELECT id_editorial FROM editorial WHERE editorial = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombreEditorial.toUpperCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_editorial");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar id_editorial: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna los nombres de todas las editoriales disponibles, ordenados por
     * ID.
     *
     * @return Lista de nombres de editoriales; vacía si no hay registros.
     */
    public ArrayList<String> obtenerTodasLasEditoriales() {
        ArrayList<String> editoriales = new ArrayList<>();
        String sql = "SELECT editorial FROM editorial ORDER BY id_editorial";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                editoriales.add(rs.getString("editorial"));
            }
        } catch (Exception e) {
            System.out.println("Error al consultar editoriales: " + e.getMessage());
        }
        return editoriales;
    }
}
