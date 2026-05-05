
package com.chatcliente;

import com.chatcliente.controlador.ControladorChat;
import com.chatcliente.servicio.ServicioClienteChat;
import com.chatcliente.vista.VistaChat;
import javax.swing.JOptionPane;

public class ClienteMain {

    public static void main(String[] args) throws Exception {

        String nombre = JOptionPane.showInputDialog("Nombre:");

        VistaChat vista = new VistaChat();
        ServicioClienteChat servicio = new ServicioClienteChat("localhost", 5010, nombre);

        new ControladorChat(vista, servicio, nombre);

        vista.setVisible(true);
    }
}