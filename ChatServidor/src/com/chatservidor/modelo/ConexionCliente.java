package com.chatservidor.modelo;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConexionCliente {

    private String nombreUsuario;
    private Socket socket;
    private ObjectOutputStream salida;

    public ConexionCliente(String nombreUsuario, Socket socket, ObjectOutputStream salida) {
        this.nombreUsuario = nombreUsuario;
        this.socket = socket;
        this.salida = salida;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }
}