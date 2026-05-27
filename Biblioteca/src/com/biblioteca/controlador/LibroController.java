package com.biblioteca.controlador;

import com.biblioteca.entidad.*;
import com.biblioteca.servicio.LibroService;
import java.util.List;
import javax.swing.*;

public class LibroController {

    private LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    public void guardar(JTextField txtIdLibro,
            JTextField txtISBN,
            JTextField txtNombre,
            JComboBox cbCategoria,
            JComboBox cbEditorial,
            JCheckBox checkActivo) {

        try {
            Libro libro = new Libro();

            libro.setIdLibro(Integer.parseInt(txtIdLibro.getText()));
            libro.setIsbn(Integer.parseInt(txtISBN.getText()));
            libro.setNombre(txtNombre.getText());

            libro.setCategoria((Categoria) cbCategoria.getSelectedItem());
            libro.setEditorial((Editorial) cbEditorial.getSelectedItem());

            libro.setActivo(checkActivo.isSelected());

            service.guardar(libro);

            JOptionPane.showMessageDialog(null, "Libro guardado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public int obtenerSiguienteId() {
        return service.obtenerSiguienteId();
    }

    public void editar(JTextField txtIdLibro,
            JTextField txtISBN,
            JTextField txtNombre,
            JComboBox<Categoria> cbCategoria,
            JComboBox<Editorial> cbEditorial,
            JCheckBox checkActivo) {

        try {
            Libro libro = new Libro();

            libro.setIdLibro(Integer.parseInt(txtIdLibro.getText()));
            libro.setIsbn(Integer.parseInt(txtISBN.getText()));
            libro.setNombre(txtNombre.getText());
            libro.setCategoria((Categoria) cbCategoria.getSelectedItem());
            libro.setEditorial((Editorial) cbEditorial.getSelectedItem());
            libro.setActivo(checkActivo.isSelected());

            service.actualizar(libro);

            JOptionPane.showMessageDialog(null, "Libro actualizado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void eliminar(JTextField txtIdLibro) {
        try {
            int id = Integer.parseInt(txtIdLibro.getText());

            service.eliminar(id);

            JOptionPane.showMessageDialog(null, "Libro eliminado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Libro buscarPorId(int id) {
        return service.buscarPorId(id);
    }

    public List<Libro> buscarPorNombre(String nombre) {
        return service.buscarPorNombre(nombre);
    }

}
