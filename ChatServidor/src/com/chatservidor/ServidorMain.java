
package com.chatservidor;

import com.chatservidor.controlador.ManejadorCliente;
import com.chatservidor.servicio.ServicioChat;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMain {

    public static void main(String[] args) throws Exception {

        ServerSocket servidor = new ServerSocket(5010);
        ServicioChat servicio = new ServicioChat();

        System.out.println("Servidor iniciado...");

        while (true) {
            Socket socket = servidor.accept();
            new Thread(new ManejadorCliente(socket, servicio)).start();
        }
    }
}