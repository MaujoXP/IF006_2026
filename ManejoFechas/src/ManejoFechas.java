import java.time.LocalDate;
import java.time.Year;
import java.time.DayOfWeek;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Clase ManejoFechas
 * Ejemplo de aplicación de convenciones de código en Java
 * Autor: Mauricio León Bermúdez C5G444
 */

/**
 * La clase ManejoFechas provee métodos para manipular y analizar
 * información relacionada con fechas, siguiendo convenciones de código en Java
 */
public class ManejoFechas {

    // Constantes: siempre en mayúsculas con guiones bajos
    private static final int DIAS_POR_ANIO = 365;
    private static final int DIAS_POR_MES = 30;

    // Atributos de ejemplo
    private LocalDate fechaBase;
    
    /**
     * Constructor vacío de clase
     */
    public ManejoFechas() {
    }

    /**
     * Constructor que inicializa la fecha base
     * @param fechaBase Fecha inicial para operaciones
     */
    public ManejoFechas(LocalDate fechaBase) {
        this.fechaBase = fechaBase;
    }

    /**
     * Obtiene la fecha actual del sistema
     * @return Fecha actual
     */
    public LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }

    /**
    * Convierte una fecha a un formato de texto específico.
    * Ejemplos de patrones de formato:
    * "dd/MM/yyyy"
    * "yyyy-MM-dd"
    * "dd-MM-yyyy"
    * "MM/dd/yyyy"
    * "dd-MMM-yyyy"
    *
    * @param fecha Fecha a convertir
    * @param patron Patrón de formato
    * @return Fecha en formato de texto
    */
   public String convertirFechaFormato(LocalDate fecha, String patron) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
       return fecha.format(formatter);
   }

   /**
 * Convierte un texto a una fecha válida.
 * Ejemplos de patrones de formato:
 * "dd-MM-yyyy"
 * "yyyy/MM/dd"
 * "MM/dd/yyyy"
 *
 * @param texto Texto que representa la fecha
 * @param patron Patrón de formato
 * @return Fecha convertida
 */
    public LocalDate convertirTextoAFecha(String texto, String patron) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
        return LocalDate.parse(texto, formatter);
    }

    /**
     * Suma una cantidad de días a una fecha.
     * @param fecha Fecha base.
     * @param dias Número de días a sumar.
     * @return Nueva fecha.
     */
    public LocalDate sumarDias(LocalDate fecha, int dias) {
        return fecha.plusDays(dias);
    }

    /**
     * Resta una cantidad de días a una fecha.
     * @param fecha Fecha base.
     * @param dias Número de días a restar.
     * @return Nueva fecha.
     */
    public LocalDate restarDias(LocalDate fecha, int dias) {
        return fecha.minusDays(dias);
    }

    /**
     * Calcula la diferencia aproximada en días entre dos fechas.
     * @param fecha1 Primera fecha.
     * @param fecha2 Segunda fecha.
     * @return Diferencia en días (aproximada).
     */
    public int calcularDiferenciaEnDias(LocalDate fecha1, LocalDate fecha2) {
        Period periodo = Period.between(fecha1, fecha2);
        return periodo.getYears() * DIAS_POR_ANIO
             + periodo.getMonths() * DIAS_POR_MES
             + periodo.getDays();
    }

    /**
     * Compara dos fechas y determina si una es anterior, posterior o igual.
     * @param fecha1 Primera fecha.
     * @param fecha2 Segunda fecha.
     * @return Resultado de la comparación.
     */
    public String compararFechas(LocalDate fecha1, LocalDate fecha2) {
        if (fecha1.isBefore(fecha2)) {
            return "La primera fecha es anterior";
        } else if (fecha1.isAfter(fecha2)) {
            return "La primera fecha es posterior";
        } else {
            return "Las fechas son iguales";
        }
    }

    /**
     * Determina si un año es bisiesto.
     * @param anio Año a evaluar.
     * @return true si es bisiesto, false en caso contrario.
     */
    public boolean esBisiesto(int anio) {
        return Year.of(anio).isLeap();
    }

    /**
     * Obtiene el día de la semana de una fecha.
     * @param fecha Fecha a evaluar.
     * @return Día de la semana.
     */
    public DayOfWeek obtenerDiaSemana(LocalDate fecha) {
        return fecha.getDayOfWeek();
    }

    /**
     * Calcula la edad a partir de una fecha de nacimiento.
     * @param fechaNacimiento Fecha de nacimiento.
     * @return Edad en años.
     */
    public int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
}

