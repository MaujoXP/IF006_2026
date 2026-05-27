package com.biblioteca.servicio;

import com.biblioteca.entidad.Usuario;
import com.biblioteca.repositorio.UsuarioRepository;
import com.biblioteca.util.PasswordUtil;

public class UsuarioService {

    private UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario login(String user, String pass) {

        Usuario u = repo.buscarPorLogin(user);

        if (u == null) {
            return null;
        }

        String hash = PasswordUtil.encriptar(pass);

        if (!u.getContrasenna().equals(hash)) {
            return null;
        }

        return u;
    }
}