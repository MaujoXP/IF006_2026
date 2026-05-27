package com.biblioteca.controlador;

import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.PrestamoLibro;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.repositorio.LibroRepositorio;
import com.biblioteca.repositorio.PrestamoRepositorio;
import com.biblioteca.repositorio.UsuarioRepositorio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

/**
 * Controlador para la gestión de préstamos y devoluciones de libros.
 * 
 * Aplica las reglas de negocio del sistema de préstamos:
 * costo de ₡350 por día y multa de ₡600 por día de atraso.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class PrestamoControlador {

    private PrestamoRepositorio prestamoRepo;
    private LibroRepositorio    libroRepo;
    private UsuarioRepositorio  usuarioRepo;

    /**
     * Crea una nueva instancia del controlador e inicializa los repositorios necesarios.
     */
    public PrestamoControlador() {
        this.prestamoRepo = new PrestamoRepositorio();
        this.libroRepo    = new LibroRepositorio();
        this.usuarioRepo  = new UsuarioRepositorio();
    }

    /**
     * Registra un nuevo préstamo de libro a un usuario
     * 
     * Verifica que el usuario exista, que el libro exista y que tenga
     * ejemplares disponibles antes de registrar el préstamo.
     * La fecha de fin debe ser posterior a la fecha de inicio.
     *
     * @param nombreUsuario Nombre de login del usuario que realiza el préstamo.
     * @param isbn          ISBN del libro a prestar.
     * @param fechaInicio   Fecha de inicio del préstamo.
     * @param fechaFin      Fecha límite de devolución.
     * @return ID del préstamo creado si es exitoso;
     *         {@code -2} si el libro no tiene ejemplares disponibles;
     *         {@code -3} si el usuario no existe;
     *         {@code -4} si el libro no existe;
     *         {@code -1} si ocurre un error general en la BD.
     */
    public int registrarPrestamo(String nombreUsuario, long isbn, LocalDate fechaInicio, LocalDate fechaFin) {
        Usuario usuario = usuarioRepo.consultarUsuarioPorNombre(nombreUsuario);
        if (usuario == null) return -3;

        Libro libro = libroRepo.consultarLibroPorIsbn(isbn);
        if (libro == null) return -4;

        if (libro.getCantidad() <= 0) return -2;

        PrestamoLibro prestamo = new PrestamoLibro(usuario, libro, fechaInicio, fechaFin);
        return prestamoRepo.registrarPrestamo(prestamo);
    }

    /**
     * Registra la devolución de un libro y calcula la multa si corresponde.
     * 
     * Si la fecha de devolución es posterior a la fecha límite del préstamo,
     * se aplica una multa de ₡600 por cada día de atraso.
     * 
     *
     * @param nombreUsuario   Nombre de login del usuario que devuelve el libro.
     * @param isbn            ISBN del libro devuelto.
     * @param fechaDevolucion Fecha real de devolución.
     * @return Monto de la multa en colones (0 si no hay atraso);
     *         {@code -2} si no se encontró el préstamo activo para ese usuario/libro;
     *         {@code -1} si ocurre un error al actualizar la BD.
     */
    public int registrarDevolucion(String nombreUsuario, long isbn, LocalDate fechaDevolucion) {
        Usuario usuario = usuarioRepo.consultarUsuarioPorNombre(nombreUsuario);
        Libro libro     = libroRepo.consultarLibroPorIsbn(isbn);
        if (usuario == null || libro == null) return -2;

        List<PrestamoLibro> activos = prestamoRepo.listarPrestamosActivos();
        PrestamoLibro prestamo = null;
        for (PrestamoLibro p : activos) {
            if (p.getUsuario().getUsuario().equals(nombreUsuario) && p.getLibro().getIsbn() == isbn) {
                prestamo = p;
                break;
            }
        }
        if (prestamo == null) return -2;

        prestamo.calcularMultaPorAtraso(fechaDevolucion);
        int multa = prestamo.getMulta();
        boolean ok = prestamoRepo.registrarDevolucion(prestamo, fechaDevolucion);
        return ok ? multa : -1;
    }

    /**
     * Retorna la lista de préstamos actualmente activos (no devueltos).
     *
     * @return Lista de objetos {@link PrestamoLibro} con estado activo.
     */
    public List<PrestamoLibro> listarActivos() {
        return prestamoRepo.listarPrestamosActivos();
    }

    /**
     * Retorna la lista de préstamos inactivos (ya devueltos).
     *
     * @return Lista de objetos {@link PrestamoLibro} con estado inactivo.
     */
    public List<PrestamoLibro> listarInactivos() {
        return prestamoRepo.listarPrestamosInactivos();
    }

    /**
     * Retorna el historial completo de préstamos (activos e inactivos).
     *
     * @return Lista de todos los objetos {@link PrestamoLibro} registrados.
     */
    public List<PrestamoLibro> listarTodos() {
        return prestamoRepo.listarTodosPrestamos();
    }

    /**
     * Retorna el historial de préstamos de un usuario específico.
     *
     * @param nombreUsuario Nombre de login del usuario.
     * @return Lista de préstamos del usuario; vacía si no existe o no tiene préstamos.
     */
    public List<PrestamoLibro> listarPorUsuario(String nombreUsuario) {
        Usuario u = usuarioRepo.consultarUsuarioPorNombre(nombreUsuario);
        if (u == null) return Collections.emptyList();
        return prestamoRepo.listarPrestamosPorUsuario(u);
    }

    /**
     * Calcula el costo estimado de un préstamo según las fechas indicadas.
     * 
     * Tarifa: ₡350 por día.
     *
     * @param inicio Fecha de inicio del préstamo.
     * @param fin    Fecha de devolución prevista.
     * @return Costo total en colones costarricenses.
     */
    public int calcularCosto(LocalDate inicio, LocalDate fin) {
        long dias = ChronoUnit.DAYS.between(inicio, fin);
        return (int) dias * 350;
    }

    /**
     * Calcula la multa potencial sin registrarla en la base de datos.
     * 
     * Útil para mostrar al usuario el costo antes de confirmar la devolución.
     * Tarifa de multa: ₡600 por día de atraso.
     *
     * @param fechaFin        Fecha límite de devolución del préstamo.
     * @param fechaDevolucion Fecha real en que se devuelve el libro.
     * @return Monto de la multa en colones; {@code 0} si no hay atraso.
     */
    public int calcularMultaSimulada(LocalDate fechaFin, LocalDate fechaDevolucion) {
        if (fechaDevolucion.isAfter(fechaFin)) {
            long dias = ChronoUnit.DAYS.between(fechaFin, fechaDevolucion);
            return (int) dias * 600;
        }
        return 0;
    }
}