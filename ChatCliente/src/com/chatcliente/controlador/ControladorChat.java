
package com.chatcliente.controlador;

import com.chatcliente.servicio.ServicioClienteChat;
import com.chatcliente.vista.VistaChat;
import com.chatcomun.modelo.Mensaje;

public class ControladorChat {

    private VistaChat vista;
    private ServicioClienteChat servicio;
    private String nombre;

    public ControladorChat(VistaChat vista, ServicioClienteChat servicio, String nombre) {
        this.vista = vista;
        this.servicio = servicio;
        this.nombre = nombre;

        iniciar();
    }

    private void iniciar() {

        vista.agregarListenerEnvio(e -> {
            try {
                String texto = vista.obtenerMensaje();
                servicio.enviarMensaje(new Mensaje(nombre, texto));
                vista.limpiarCampo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        servicio.escuchar(mensaje -> {
            vista.mostrarMensaje(
                mensaje.getRemitente() + ": " + mensaje.getContenido()
            );
        });
    }
}