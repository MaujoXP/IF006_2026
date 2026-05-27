
package com.biblioteca;

import com.biblioteca.controlador.LoginController;
import com.biblioteca.repositorio.UsuarioRepository;
import com.biblioteca.servicio.UsuarioService;
import com.biblioteca.presentacion.JFLogin;

public class Main {
    public static void main(String[] args) {

        UsuarioRepository repo = new UsuarioRepository();
        UsuarioService service = new UsuarioService(repo);
        LoginController controller = new LoginController(service);

        java.awt.EventQueue.invokeLater(() -> {
            new JFLogin(controller).setVisible(true);
        });
    }
}
