package utilidades;


import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class Main {
    public static void main(String[] args) {
        ManejoFechas prueba = new ManejoFechas();
        LocalDate hoy = prueba.obtenerFechaActual();
        System.out.println("Hoy: " + hoy);

        System.out.println("Formato: " + prueba.convertirFechaFormato(hoy, "dd/MM/yyyy"));

        LocalDate fechaTexto = prueba.convertirTextoAFecha("23-03-2026", "dd-MM-yyyy");
        System.out.println("Fecha desde texto: " + fechaTexto);

        System.out.println("Sumar 5 días: " + prueba.sumarDias(hoy, 5));
        System.out.println("Restar 3 días: " + prueba.restarDias(hoy, 3));

        LocalDate otraFecha = LocalDate.of(2025, 12, 31);
        System.out.println("Diferencia en días: " + prueba.calcularDiferenciaEnDias(hoy, otraFecha));

        System.out.println(prueba.compararFechas(hoy, otraFecha));

        System.out.println("2024 es bisiesto? " + prueba.esBisiesto(2024));

        System.out.println("Día de la semana: " + prueba.obtenerDiaSemana(hoy));

        LocalDate nacimiento = LocalDate.of(2004, 7, 14);
        System.out.println("Edad: " + prueba.calcularEdad(nacimiento));
    }
}
