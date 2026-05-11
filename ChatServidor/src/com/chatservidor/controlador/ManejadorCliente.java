package com.chatservidor.controlador;

import com.chatcomun.modelo.Mensaje;
import com.chatservidor.modelo.ConexionCliente;
import com.chatservidor.servicio.ServicioChat;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ManejadorCliente implements Runnable {

    private Socket socket;
    private ServicioChat servicioChat;

    public ManejadorCliente(Socket socket, ServicioChat servicioChat) {
        this.socket = socket;
        this.servicioChat = servicioChat;
    }

    @Override
    public void run() {
        try (
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
        ) {
            String nombre = (String) entrada.readObject();

            ConexionCliente cliente = new ConexionCliente(nombre, socket, salida);
            servicioChat.agregarCliente(cliente);

            while (true) {
                Mensaje mensaje = (Mensaje) entrada.readObject();
                servicioChat.enviarATodos(mensaje);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}