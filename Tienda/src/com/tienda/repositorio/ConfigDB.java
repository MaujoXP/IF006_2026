package com.tienda.repositorio;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Gaby
 */
public class ConfigDB {

    private static Properties properties = new Properties();

    static {
        try (
                InputStream input
                = ConfigDB.class
                        .getClassLoader()
                        .getResourceAsStream("com/tienda/repositorio/.env")) {

                    if (input == null) {
                        System.out.println("No se encontró el archivo .env");
                    }
                    properties.load(input);
                } catch (Exception e) {
                    System.out.println("Error cargando configuracion: " + e.getMessage());
                }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

}
