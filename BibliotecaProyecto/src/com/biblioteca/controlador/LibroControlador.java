package com.biblioteca.controlador;

import com.biblioteca.modelo.Autor;
import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Categoria;
import com.biblioteca.modelo.Editorial;
import com.biblioteca.repositorio.LibroRepositorio;
import com.biblioteca.repositorio.CategoriaRepositorio;
import com.biblioteca.repositorio.EditorialRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la gestión del catálogo de libros.
 * 
 * Centraliza las operaciones de registro, búsqueda, actualización
 * y eliminación de libros, aplicando validaciones antes de persistir.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class LibroControlador {

    private LibroRepositorio    libroRepo;
    private CategoriaRepositorio categoriaRepo;
    private EditorialRepositorio editorialRepo;

    /**
     * Crea una nueva instancia del controlador e inicializa los repositorios necesarios.
     */
    public LibroControlador() {
        this.libroRepo     = new LibroRepositorio();
        this.categoriaRepo = new CategoriaRepositorio();
        this.editorialRepo = new EditorialRepositorio();
    }

    /**
     * Registra un nuevo libro en el sistema con su autor asociado.
     *
     * @param isbn             ISBN del libro (7–13 dígitos).
     * @param nombre           Título del libro.
     * @param cantidad         Número de ejemplares disponibles.
     * @param categoriaStr     Nombre de la categoría (debe coincidir con un valor del enum {@link Categoria}).
     * @param editorialStr     Nombre de la editorial (debe coincidir con un valor del enum {@link Editorial}).
     * @param autorNombre      Nombre completo del autor.
     * @param autorNacionalidad Nacionalidad del autor.
     * @param autorSeudonimo   Seudónimo del autor (puede estar vacío).
     * @param autorFecha       Fecha de nacimiento del autor.
     * @return ID del libro registrado si es exitoso;
     *         {@code -2} si hay campos inválidos o vacíos;
     *         {@code -1} si ocurre un error en la base de datos.
     */
    public int registrarLibro(long isbn, String nombre, int cantidad,
                              String categoriaStr, String editorialStr,
                              String autorNombre, String autorNacionalidad,
                              String autorSeudonimo, LocalDate autorFecha) {

        // Validar ISBN: entre 7 y 13 dígitos, positivo
        String isbnStr = String.valueOf(isbn);
        if (isbn <= 0 || isbnStr.length() < 7 || isbnStr.length() > 13) return -2;

        // Validar nombre del libro
        if (nombre == null || nombre.trim().length() < 2) return -2;

        // Validar cantidad
        if (cantidad < 1) return -2;

        // Validar nombre del autor
        if (autorNombre == null || autorNombre.trim().length() < 3) return -2;

        // Validar fecha de nacimiento del autor (no puede ser futura)
        if (autorFecha == null || autorFecha.isAfter(LocalDate.now())) return -2;

        Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());
        Editorial editorial = Editorial.valueOf(editorialStr.toUpperCase());

        Autor autor = new Autor(autorNombre.trim(), autorNacionalidad, autorSeudonimo, autorFecha);
        Libro libro = new Libro(isbn, nombre.trim(), autor, categoria, editorial, cantidad, true);
        return libroRepo.registrarLibro(libro);
    }

    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn ISBN del libro a buscar.
     * @return El objeto {@link Libro} encontrado, o {@code null} si no existe.
     */
    public Libro buscarPorIsbn(long isbn) {
        return libroRepo.consultarLibroPorIsbn(isbn);
    }

    /**
     * Actualiza la cantidad de ejemplares disponibles de un libro.
     *
     * @param isbn     ISBN del libro a actualizar.
     * @param cantidad Nueva cantidad (debe ser mayor o igual a 0).
     * @return {@code true} si se actualizó correctamente; {@code false} en caso contrario.
     */
    public boolean actualizarCantidad(long isbn, int cantidad) {
        if (cantidad < 0) return false;
        return libroRepo.actualizarCantidad(isbn, cantidad);
    }

    /**
     * Elimina un libro del catálogo por su ISBN.
     *
     * @param isbn ISBN del libro a eliminar.
     * @return {@code true} si se eliminó; {@code false} si no se encontró o tiene préstamos activos.
     */
    public boolean eliminarLibro(long isbn) {
        return libroRepo.eliminarLibroPorIsbn(isbn);
    }

    /**
     * Retorna la lista completa de libros registrados en el sistema.
     *
     * @return Lista de objetos {@link Libro}; vacía si no hay ninguno.
     */
    public List<Libro> listarTodos() {
        return libroRepo.consultarTodosLosLibros();
    }

    /**
     * Busca libros filtrando por un campo específico y un valor de búsqueda.
     * 
     * Los campos válidos son: {@code nombre}, {@code autor}, {@code categoria},
     * {@code editorial}, {@code isbn}. La búsqueda por texto es insensible a mayúsculas.
     *
     * @param campo Campo por el cual filtrar.
     * @param valor Valor a buscar dentro del campo.
     * @return Lista de libros que coinciden con el criterio.
     */
    public List<Libro> buscarPorCriteria(String campo, String valor) {
        List<Libro> todos = libroRepo.consultarTodosLosLibros();
        List<Libro> resultado = new ArrayList<>();
        for (Libro l : todos) {
            boolean coincide = false;
            switch (campo) {
                case "nombre":
                    coincide = l.getNombre().toLowerCase().contains(valor.toLowerCase());
                    break;
                case "autor":
                    coincide = l.getAutor().getNombreCompleto().toLowerCase().contains(valor.toLowerCase());
                    break;
                case "categoria":
                    coincide = l.getCategoria().getDescripcion().toLowerCase().contains(valor.toLowerCase());
                    break;
                case "editorial":
                    coincide = l.getEditorial().getNombre().toLowerCase().contains(valor.toLowerCase());
                    break;
                case "isbn":
                    try { coincide = l.getIsbn() == Long.parseLong(valor); }
                    catch (NumberFormatException ignored) {}
                    break;
                default:
                    coincide = true;
            }
            if (coincide) resultado.add(l);
        }
        return resultado;
    }

    /**
     * Obtiene la lista de nombres de todas las categorías disponibles en la BD.
     *
     * @return Lista de nombres de categorías.
     */
    public ArrayList<String> getCategorias() {
        return categoriaRepo.obtenerTodasLasCategorias();
    }

    /**
     * Obtiene la lista de nombres de todas las editoriales disponibles en la BD.
     *
     * @return Lista de nombres de editoriales.
     */
    public ArrayList<String> getEditoriales() {
        return editorialRepo.obtenerTodasLasEditoriales();
    }
}