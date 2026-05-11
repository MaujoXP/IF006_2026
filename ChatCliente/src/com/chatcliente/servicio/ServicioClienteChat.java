
package com.chatcliente.servicio;


import com.chatcomun.modelo.Mensaje;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServicioClienteChat {

    private ObjectOutputStream salida;
    private ObjectInputStream entrada;

    public ServicioClienteChat(String host, int puerto, String nombre) throws Exception {

        Socket socket = new Socket(host, puerto);

        salida = new ObjectOutputStream(socket.getOutputStream());
        entrada = new ObjectInputStream(socket.getInputStream());

        salida.writeObject(nombre);
    }

    public void enviarMensaje(Mensaje mensaje) throws Exception {
        salida.writeObject(mensaje);
    }

    public void escuchar(MensajeListener listener) {
        new Thread(() -> {
            try {
                while (true) {
                    Mensaje mensaje = (Mensaje) entrada.readObject();
                    listener.alRecibir(mensaje);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public interface MensajeListener {
        void alRecibir(Mensaje mensaje);
    }
}