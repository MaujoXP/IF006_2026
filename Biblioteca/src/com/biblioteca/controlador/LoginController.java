package com.biblioteca.controlador;

import com.biblioteca.entidad.Usuario;
import com.biblioteca.servicio.UsuarioService;
import javax.swing.*;

public class LoginController {

    private UsuarioService service;

    public LoginController(UsuarioService service) {
        this.service = service;
    }

    public Usuario login(JTextField txtUsuario, JPasswordField txtPassword) {

        String user = txtUsuario.getText();
        String pass = new String(txtPassword.getPassword());

        return service.login(user, pass);
    }
}