package com.biblioteca.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión JDBC con la base de datos MySQL.
 *
 * Lee los parámetros de conexión (driver, URL, usuario, contraseña) desde el
 * archivo {@code .env} a través de {@link ConfigDB}.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class ConexionDB {

    /**
     * Abre y retorna una nueva conexión a la base de datos.
     *
     * @return Objeto {@link Connection} listo para usarse, o {@code null} si
     * ocurre un error al conectar.
     */
    public static Connection obtenerConexion() {
        try {
            Class.forName(ConfigDB.get("DB_DRIVER"));
            return DriverManager.getConnection(
                    ConfigDB.get("DB_URL"),
                    ConfigDB.get("DB_USER"),
                    ConfigDB.get("DB_PASSWORD"));
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
            return null;
        }
    }

    /**
     * Cierra una conexión abierta de forma segura.
     *
     * No hace nada si la conexión es {@code null} o ya está cerrada.
     *
     * @param conn Conexión a cerrar.
     */
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Conexion cerrada satisfactoriamente");
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
