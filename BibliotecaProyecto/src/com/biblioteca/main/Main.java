/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.main;

import com.biblioteca.controlador.LoginControlador;
import com.biblioteca.modelo.Persona;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.repositorio.ConexionDB;
import com.biblioteca.repositorio.UsuarioRepositorio;
import com.biblioteca.util.ContraseniaUtilidad;
import com.biblioteca.vista.JFLogin;
import java.sql.Connection;
//import com.biblioteca.vista.JFLoginALT;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Meowricio
 */
public class Main {
    
    public static void main(String[] args) {
        cargarDatosIniciales();
        JFLogin login = new JFLogin();
        login.setVisible(true);

    }

    private static void cargarDatosIniciales() {
        UsuarioRepositorio ur = new UsuarioRepositorio();
        String usuarioAdmin = "ADMIN";
        String contrasenia = "123";
            Usuario usuario = new Usuario("11111111", "ADMIN", "ADMIN", "1111-1111", "BIBLIOTECA", usuarioAdmin, contrasenia, true);
            ur.agregarUsuario(usuario, 1);
            System.out.println("Usuario ADMIN creado correctamente.");
    }

}
