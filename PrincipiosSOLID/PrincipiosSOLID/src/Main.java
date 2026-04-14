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
        INotificacion canal = new EmailNotificacion();
        
        Notificador notificador = new Notificador(canal);
        String mensaje = "Este es un mensaje";
        notificador.notificar(mensaje);
        Historial historial = new Historial();
        historial.guardar(mensaje);
        Reporte reporte = new Reporte();
        reporte.generar();
    }
}
