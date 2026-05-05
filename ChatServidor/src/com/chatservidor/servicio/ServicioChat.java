
package com.chatservidor.servicio;

import com.chatcomun.modelo.Mensaje;
import com.chatservidor.modelo.ConexionCliente;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServicioChat {

    private List<ConexionCliente> clientes = new CopyOnWriteArrayList<>();

    public void agregarCliente(ConexionCliente cliente) {
        clientes.add(cliente);
    }

    public void eliminarCliente(ConexionCliente cliente) {
        clientes.remove(cliente);
    }

    public void enviarATodos(Mensaje mensaje) {
        for (ConexionCliente cliente : clientes) {
            try {
                cliente.getSalida().writeObject(mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}