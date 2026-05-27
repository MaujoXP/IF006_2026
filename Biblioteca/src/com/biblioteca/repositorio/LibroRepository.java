package com.biblioteca.repositorio;

import com.biblioteca.entidad.Libro;
import java.util.ArrayList;
import java.util.List;

public class LibroRepository {

    private List<Libro> listaLibros = new ArrayList<>();

    public void guardar(Libro libro) {
        listaLibros.add(libro);
    }

    public List<Libro> listar() {
        return listaLibros.stream()
                .filter(Libro::isActivo)
                .toList();
    }

    public int obtenerSiguienteId() {
        if (listaLibros.isEmpty()) {
            return 1;
        }

        int max = 0;

        for (Libro l : listaLibros) {
            if (l.getIdLibro() > max) {
                max = l.getIdLibro();
            }
        }

        return max + 1;
    }

    public void actualizar(Libro libro) {
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getIdLibro() == libro.getIdLibro()) {
                listaLibros.set(i, libro);
                return;
            }
        }
    }
    
    public List<Libro> listarTodos() {
        return listaLibros;
    }

    public void eliminar(int id) {
        Libro libro = buscarPorId(id);
        if (libro != null) {
            libro.setActivo(false);
        }
    }

    public Libro buscarPorId(int id) {
        return listaLibros.stream()
                .filter(l -> l.getIdLibro() == id && l.isActivo())
                .findFirst()
                .orElse(null);
    }

    public List<Libro> buscarPorNombre(String nombre) {
        return listaLibros.stream()
                .filter(l -> l.isActivo())
                .filter(l -> l.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }

}
