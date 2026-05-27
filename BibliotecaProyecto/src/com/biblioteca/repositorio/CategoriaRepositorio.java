package com.biblioteca.repositorio;

import java.sql.*;
import java.util.ArrayList;

/**
 * Repositorio de solo lectura para la entidad Categoría.
 *
 * Consulta la tabla {@code categoria} de la base de datos.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class CategoriaRepositorio {

    ConexionDB cnx;

    /**
     * Crea una nueva instancia del repositorio.
     */
    public CategoriaRepositorio() {
        cnx = new ConexionDB();
    }

    /**
     * Busca el ID de una categoría por su nombre.
     *
     * @param nombreCategoria Nombre de la categoría a buscar (se normaliza a
     * mayúsculas).
     * @return ID de la categoría si existe; {@code null} si no se encuentra.
     */
    public Integer consultarIdCategoriaPorNombre(String nombreCategoria) {
        String sql = "SELECT id_categoria FROM categoria WHERE categoria = ?";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombreCategoria.toUpperCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_categoria");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar id_categoria: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna los nombres de todas las categorías disponibles, ordenados por
     * ID.
     *
     * @return Lista de nombres de categorías; vacía si no hay registros.
     */
    public ArrayList<String> obtenerTodasLasCategorias() {
        ArrayList<String> categorias = new ArrayList<>();
        String sql = "SELECT categoria FROM categoria ORDER BY id_categoria";
        try (Connection cn = cnx.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("categoria"));
            }
        } catch (Exception e) {
            System.out.println("Error al consultar categorías: " + e.getMessage());
        }
        return categorias;
    }
}
