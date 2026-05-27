package com.biblioteca.util;

import java.security.MessageDigest;

/**
 * Utilidad para el manejo seguro de contraseñas mediante hash SHA-256.
 * 
 * No almacena estado; todos sus métodos son estáticos.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class ContraseniaUtilidad {

    /**
     * Encripta una contraseña aplicando el algoritmo SHA-256 y retornando el
     * resultado en representación hexadecimal.
     *
     * @param password Contraseña en texto plano.
     * @return Cadena hexadecimal de 64 caracteres con el hash SHA-256.
     * @throws RuntimeException si el algoritmo SHA-256 no está disponible en la
     * JVM.
     */
    public static String encriptar(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                String s = Integer.toHexString(0xff & b);
                if (s.length() == 1) {
                    hex.append('0');
                }
                hex.append(s);
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar contraseña");
        }
    }
}
