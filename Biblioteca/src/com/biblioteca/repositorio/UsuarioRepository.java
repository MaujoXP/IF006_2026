package com.biblioteca.repositorio;

import com.biblioteca.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepository() {
        cargarDatosIniciales();
    }
    
    private void cargarDatosIniciales() {

        Usuario admin = new Usuario();
        admin.setIdUsuario(1);
        admin.setNombreLogin("admin");
        admin.setContrasenna(
            com.biblioteca.util.PasswordUtil.encriptar("123")
        );
        admin.setPerfil("ADMIN");
        admin.setActivo(true);

        usuarios.add(admin);
    }

    public Usuario buscarPorLogin(String login) {
        return usuarios.stream()
                .filter(u -> u.getNombreLogin().equals(login) && u.isActivo())
                .findFirst()
                .orElse(null);
    }
}