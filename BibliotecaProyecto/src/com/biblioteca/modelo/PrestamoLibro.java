package com.biblioteca.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Representa un préstamo de libro realizado a un usuario.
 * 
 * Encapsula la lógica de cálculo de costo (₡350/día) y multa por atraso
 * (₡600/día). El costo total se calcula automáticamente al crear el objeto.
 *
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class PrestamoLibro {

    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int costoTotal;
    private int multa;
    private boolean activo;

    /**
     * Crea un nuevo préstamo y calcula su costo total automáticamente.
     *
     * @param usuario Usuario que realiza el préstamo.
     * @param libro Libro prestado.
     * @param fechaInicio Fecha de inicio del préstamo.
     * @param fechaFin Fecha límite de devolución.
     */
    public PrestamoLibro(Usuario usuario, Libro libro,
            LocalDate fechaInicio, LocalDate fechaFin) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costoTotal = calcularCosto();
        this.multa = 0;
        this.activo = true;
    }

    /**
     * Calcula el costo del préstamo a razón de ₡350 por día.
     *
     * @return Costo total en colones costarricenses.
     */
    private int calcularCosto() {
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return (int) dias * 350;
    }

    /**
     * Calcula y asigna la multa por atraso en la devolución.
     * 
     * Si la fecha de devolución es posterior a la fecha límite, se aplica una
     * multa de ₡600 por cada día de atraso. Si la devolución es a tiempo, la
     * multa se establece en 0.
     *
     * @param fechaDevolucion Fecha real en que se devuelve el libro.
     */
    public void calcularMultaPorAtraso(LocalDate fechaDevolucion) {
        if (fechaDevolucion.isAfter(fechaFin)) {
            long diasAtraso = ChronoUnit.DAYS.between(fechaFin, fechaDevolucion);
            this.multa = (int) diasAtraso * 600;
        } else {
            this.multa = 0;
        }
    }

    /**
     * @return Usuario que realizó el préstamo.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @return Libro prestado.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * @return Fecha de inicio del préstamo.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @return Fecha límite de devolución.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * @return Costo total del préstamo en colones.
     */
    public int getCostoTotal() {
        return costoTotal;
    }

    /**
     * @return Multa acumulada por atraso en colones; 0 si no hay atraso.
     */
    public int getMulta() {
        return multa;
    }

    /**
     * @return {@code true} si el préstamo aún está activo (libro no devuelto).
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo Estado del préstamo.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
