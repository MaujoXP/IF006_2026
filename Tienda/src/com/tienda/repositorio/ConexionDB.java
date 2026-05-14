/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gaby
 */
public class ConexionDB {

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
    
    public static void cerrarConexion(Connection conn) {
        if(conn != null) {
            try {
                if(!conn.isClosed()) {
                    conn.close();
                    System.out.println("Conexion cerrada satisfactoriamentef");
                }
            } catch(SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
