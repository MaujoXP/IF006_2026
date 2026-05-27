package com.biblioteca.repositorio;

import java.io.InputStream;
import java.util.Properties;

/**
 * Carga y expone la configuración de la base de datos desde el archivo
 * {@code .env}.
 *
 * El archivo {@code .env} debe ubicarse en
 * {@code src/com/biblioteca/repositorio/.env} y contener las claves:
 * {@code DB_DRIVER}, {@code DB_URL}, {@code DB_USER} y {@code DB_PASSWORD}.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class ConfigDB {

    private static Properties properties = new Properties();

    static {
        try (InputStream input
                = ConfigDB.class.getResourceAsStream("/com/biblioteca/repositorio/.env")) {
            if (input == null) {
                System.out.println("No se encontró el archivo .env");
            }
            properties.load(input);
        } catch (Exception e) {
            System.out.println("Error cargando configuracion: " + e.getMessage());
        }
    }

    /**
     * Retorna el valor asociado a una clave del archivo de configuración.
     *
     * @param key Nombre de la propiedad a obtener (ej: {@code "DB_URL"}).
     * @return Valor de la propiedad, o {@code null} si no existe.
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
}
