package com.biblioteca.servicio;

import com.biblioteca.entidad.Libro;
import com.biblioteca.repositorio.LibroRepository;
import java.util.List;

public class LibroService {

    private LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    public void guardar(Libro libro) {
        if (libro.getNombre() == null || libro.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        repo.guardar(libro);
    }

    public List<Libro> listar() {
        return repo.listar();
    }

    public int obtenerSiguienteId() {
        return repo.obtenerSiguienteId();
    }

    public void actualizar(Libro libro) {
        repo.actualizar(libro);
    }

    public void eliminar(int id) {
        repo.eliminar(id);
    }

    public Libro buscarPorId(int id) {
        return repo.buscarPorId(id);
    }

    public List<Libro> buscarPorNombre(String nombre) {
        return repo.buscarPorNombre(nombre);
    }
}
