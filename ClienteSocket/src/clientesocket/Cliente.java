/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Gaby
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1"; //IP
        int puerto = 5000; //Puerto

        try (Socket socket = new Socket(host, puerto); 
                BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())); 
                PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(), true) ) {
            //Envio de datos al servidor
            salida.println("Hola servidor");
            //Recepcion de respuesta del servidor
            String respuesta = entrada.readLine();
            System.out.println("Servidor: " + respuesta);
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
